package org.hibernate.studentmanagement.service.student;

import org.hibernate.studentmanagement.dao.CourseRepository;
import org.hibernate.studentmanagement.dao.StudentRepository;
import org.hibernate.studentmanagement.dto.student.StudentDTO;
import org.hibernate.studentmanagement.dto.student.StudentDTOMapper;
import org.hibernate.studentmanagement.entity.Course;
import org.hibernate.studentmanagement.entity.Student;
import org.hibernate.studentmanagement.exception.CourseAlreadyExistsException;
import org.hibernate.studentmanagement.exception.CourseNotFoundException;
import org.hibernate.studentmanagement.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.studentmanagement.exception.ErrorMessage.COURSE_NOT_FOUND;
import static org.hibernate.studentmanagement.exception.ErrorMessage.STUDENT_NOT_FOUND;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentDTOMapper studentDTOMapper;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentDTOMapper studentDTOMapper,
                              CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.studentDTOMapper = studentDTOMapper;
        this.courseRepository = courseRepository;
    }


    @Override
    public StudentDTO updateStudent(Integer studentId, Student student) {

        Student updatedStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format(STUDENT_NOT_FOUND.getMessage(), studentId)));

        updatedStudent.setFirstName(student.getFirstName());
        updatedStudent.setLastName(student.getLastName());
        updatedStudent.setEmail(student.getEmail());

        Student save = studentRepository.save(updatedStudent);

        return new StudentDTO(updatedStudent.getFirstName(),
                updatedStudent.getLastName(),
                updatedStudent.getEmail());
    }

    @Override
    public void addStudent(Student student) {

        studentRepository.save(student);
    }

    @Override
    public StudentDTO findStudentById(Integer studentId) {
        return studentRepository.findById(studentId)
                .map(studentDTOMapper)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format(STUDENT_NOT_FOUND.getMessage(), studentId)));

    }

    @Override
    public List<StudentDTO> findAllStudent() {

        return studentRepository.findAll()
                .stream()
                .map(studentDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStudentById(Integer studentId) {

        boolean existById = studentRepository.existsById(studentId);

        if (!existById) {
            throw new StudentNotFoundException(
                    String.format(STUDENT_NOT_FOUND.getMessage(), studentId));
        }

        studentRepository.deleteById(studentId);
    }

    @Override
    public void addCourse(Integer courseId, Integer studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format(STUDENT_NOT_FOUND.getMessage(), studentId)));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format(COURSE_NOT_FOUND.getMessage(), courseId)));

        if (student.getCourses().contains(course)) {

            throw new CourseAlreadyExistsException("Course already exists");
        }

        student.addCourse(course);

        studentRepository.save(student);

    }

    @Override
    public List<String> selectAllStudentsByCourseId(Integer courseId) {
        boolean isExists = courseRepository.existsById(courseId);

        if (! isExists) {
            throw  new CourseNotFoundException(
                    String.format(COURSE_NOT_FOUND.getMessage(), courseId));
        }

        return studentRepository.selectAllStudentsByCourseId(courseId);
    }


}
