package org.hibernate.studentmanagement.dto.student;

import org.hibernate.studentmanagement.entity.Student;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentDTOMapper implements Function<Student, StudentDTO> {

    @Override
    public StudentDTO apply(Student student) {

        return new StudentDTO(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail());
    }
}
