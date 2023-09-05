package org.hibernate.studentmanagement.service.instructor;

import org.hibernate.studentmanagement.dao.InstructorRepository;
import org.hibernate.studentmanagement.dto.instructor.InstructorDTO;
import org.hibernate.studentmanagement.dto.instructor.InstructorDTOMapper;
import org.hibernate.studentmanagement.entity.Course;
import org.hibernate.studentmanagement.entity.Instructor;
import org.hibernate.studentmanagement.entity.InstructorDetail;
import org.hibernate.studentmanagement.exception.InstructorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.studentmanagement.exception.ErrorMessage.INSTRUCTOR_NOT_FOUND;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorDTOMapper instructorDTOMapper;


    public InstructorServiceImpl(InstructorRepository instructorRepository,
                                 InstructorDTOMapper instructorDTOMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorDTOMapper = instructorDTOMapper;
    }

    @Override
    public List<InstructorDTO> findAllInstructor() {
        return instructorRepository.findAll()
                .stream()
                .map(instructorDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public InstructorDTO findById(Integer instructorId) {

        return instructorRepository.findById(instructorId)
                .map(instructorDTOMapper)
                .orElseThrow(() -> new InstructorNotFoundException(
                        String.format(INSTRUCTOR_NOT_FOUND.getMessage(), instructorId)));
    }

    @Override
    public InstructorDTO findByName(String name) {

        return instructorRepository.findByFirstName(name)
                .map(instructorDTOMapper)
                .orElseThrow(() -> new InstructorNotFoundException(
                        String.format("Instructor with name %s not found", name)));
    }

    @Override
    public InstructorDTO findByLastNameIgnoreCase(String lastName) {

        return instructorRepository.findByLastNameEqualsIgnoreCase(lastName)
                .map(instructorDTOMapper)
                .orElseThrow(() -> new InstructorNotFoundException(
                        String.format("Instructor with lastName %s not found", lastName)));
    }

    @Override
    public InstructorDTO updateInstructor(Integer instructorId, InstructorDTO instructor) {

        Instructor currentInstructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InstructorNotFoundException(
                        String.format(INSTRUCTOR_NOT_FOUND.getMessage(), instructorId)));

        currentInstructor.setEmail(instructor.email());
        currentInstructor.setFirstName(instructor.firstName());
        currentInstructor.setLastName(instructor.lastName());
        currentInstructor.getInstructorDetail().setHobby(instructor.hobby());
        currentInstructor.getInstructorDetail().setYoutubeChanel(instructor.youtubeChanel());

        instructorRepository.save(currentInstructor);

        return new InstructorDTO(currentInstructor.getFirstName(), currentInstructor.getLastName(),
                currentInstructor.getEmail(), currentInstructor.getInstructorDetail().getYoutubeChanel(),
                currentInstructor.getInstructorDetail().getHobby());
    }

    @Override
    public void deleteInstructorById(Integer instructorId) {

        Instructor deletedInstructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InstructorNotFoundException(
                        String.format(INSTRUCTOR_NOT_FOUND.getMessage(), instructorId)));


        List<Course> courses = deletedInstructor.getCourses();

        courses.forEach(course -> course.setInstructor(null));


        instructorRepository.deleteById(instructorId);

    }

    @Override
    public void addInstructorWithDetails(InstructorDTO instructorDTO) {

        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorDTO.firstName());
        instructor.setLastName(instructorDTO.lastName());
        instructor.setEmail(instructorDTO.email());

        InstructorDetail instructorDetail = new InstructorDetail();
        instructorDetail.setHobby(instructorDTO.hobby());
        instructorDetail.setYoutubeChanel(instructorDTO.youtubeChanel());

        instructor.setInstructorDetail(instructorDetail);

        instructorRepository.save(instructor);


    }
}
