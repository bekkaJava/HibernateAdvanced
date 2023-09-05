package org.hibernate.studentmanagement.dto.student;

public record StudentDTO(
        String firstName,
        String lastName,
        String email) {
}
