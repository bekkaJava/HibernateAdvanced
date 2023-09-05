package org.hibernate.studentmanagement.service.course;

import org.hibernate.studentmanagement.dto.course.CourseDTO;
import org.hibernate.studentmanagement.entity.Course;

import java.util.List;

public interface CourseService {

    CourseDTO findCourseById(Integer id);

    List<CourseDTO> findAllCourses();

    List<String> findCoursesByInstructorId(Integer id);

    void addCourseWithInstructor(Integer instructorId, Course course);

    CourseDTO findCourseByTitle(String title);

    CourseDTO updateCourse(Integer courseId, CourseDTO course);

    void deleteCourseById(Integer courseId);

    void addStudent(Integer courseId, Integer studentId);
    List<String> selectAllCoursesByStudentId(Integer studentId);
}
