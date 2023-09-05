package org.hibernate.studentmanagement.service.course;

import org.hibernate.studentmanagement.dao.CourseRepository;
import org.hibernate.studentmanagement.dao.InstructorRepository;
import org.hibernate.studentmanagement.dao.StudentRepository;
import org.hibernate.studentmanagement.dto.course.CourseDTO;
import org.hibernate.studentmanagement.dto.course.CourseDTOMapper;
import org.hibernate.studentmanagement.entity.Course;
import org.hibernate.studentmanagement.entity.Instructor;
import org.hibernate.studentmanagement.entity.Student;
import org.hibernate.studentmanagement.exception.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.studentmanagement.exception.ErrorMessage.*;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;
    private final CourseDTOMapper courseDTOMapper;


    public CourseServiceImpl(CourseRepository courseRepository,
                             InstructorRepository instructorRepository,
                             StudentRepository studentRepository,
                             CourseDTOMapper courseDTOMapper) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
        this.courseDTOMapper = courseDTOMapper;
    }

    @Override
    public CourseDTO findCourseById(Integer courseId) {

        return courseRepository.findById(courseId)
                .map(courseDTOMapper)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format(COURSE_NOT_FOUND.getMessage(),courseId)));

    }

    @Override
    public List<CourseDTO> findAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(courseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findCoursesByInstructorId(Integer id) {

        return courseRepository.findCoursesByInstructorId(id);
    }

    @Override
    public void addCourseWithInstructor(Integer instructorId, Course course) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(()-> new InstructorNotFoundException(
                        String.format(INSTRUCTOR_NOT_FOUND.getMessage(), instructorId)));

        instructor.addCourse(course);

        instructorRepository.save(instructor);
    }

    @Override
    public CourseDTO findCourseByTitle(String title) {

        return courseRepository.findCourseByTitleEqualsIgnoreCase(title)
                .map(courseDTOMapper)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format("course with title : %s not found", title)));
    }

    @Override
    public CourseDTO updateCourse(Integer courseId, CourseDTO course) {

        Course updatedCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(
                String.format(COURSE_NOT_FOUND.getMessage(), courseId)));

        updatedCourse.setTitle(course.title());

        courseRepository.save(updatedCourse);

        return new CourseDTO(updatedCourse.getTitle());

    }

    @Override
    public void deleteCourseById(Integer courseId) {

        boolean existsBydId = courseRepository.existsById(courseId);

        if (! existsBydId) {
            throw new CourseNotFoundException(String.format(
                      COURSE_NOT_FOUND.getMessage(), courseId));
        }

        courseRepository.deleteById(courseId);


    }

    @Override
    public void addStudent(Integer courseId, Integer studentId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format(COURSE_NOT_FOUND.getMessage(), courseId)));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format(STUDENT_NOT_FOUND.getMessage(), studentId)));

        if (course.getStudents().contains(student)) {
            throw new StudentAlreadyExistsException("Student already exists");
        }

        course.addStudent(student);

        courseRepository.save(course);


    }

    @Override
    public List<String> selectAllCoursesByStudentId(Integer studentId) {

        boolean isExists = studentRepository.existsById(studentId);
        if (! isExists ) {
            throw  new StudentNotFoundException(String.format(STUDENT_NOT_FOUND.getMessage(), studentId));
        }

        return courseRepository.selectAllCoursesByStudentId(studentId);
    }


}
