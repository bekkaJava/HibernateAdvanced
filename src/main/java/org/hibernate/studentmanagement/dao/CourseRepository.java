package org.hibernate.studentmanagement.dao;

import org.hibernate.studentmanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {


    Optional<Course> findCourseByTitleEqualsIgnoreCase(String title);

    @Query(value = "select c.title from course c where c.instructor_id = ?1",
            nativeQuery = true)
    List<String> findCoursesByInstructorId(Integer id);


    @Query(value = "select c.title from course c inner join course_student ct on c.id = ct.course_id " +
            "where ct.student_id =?1", nativeQuery = true)
    List<String> selectAllCoursesByStudentId(Integer studentId);

}
