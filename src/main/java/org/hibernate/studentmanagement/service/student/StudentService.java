package org.hibernate.studentmanagement.service.student;

import org.hibernate.studentmanagement.dto.student.StudentDTO;
import org.hibernate.studentmanagement.entity.Course;
import org.hibernate.studentmanagement.entity.Student;

import java.util.List;

public interface StudentService {
    StudentDTO findStudentById(Integer studentId);
    List<StudentDTO>  findAllStudent();
    StudentDTO updateStudent(Integer studentId, Student student);
    void addStudent(Student student);
    void deleteStudentById(Integer studentId);
    void addCourse(Integer studentId, Integer courseId);
    List<String> selectAllStudentsByCourseId(Integer courseId);


}
