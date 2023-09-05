package org.hibernate.studentmanagement.dao;

import org.hibernate.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {


    @Query(value = "select  s.first_name, s.last_name, s.email from student s\n" +
            "inner join course_student ct on s.id = ct.student_id\n" +
            "where ct.course_id =?1", nativeQuery = true)
     List<String> selectAllStudentsByCourseId(Integer courseId);

}
