package org.hibernate.studentmanagement.dto.instructor;

public record InstructorDTO(
        String firstName,
        String lastName,
        String email,
        String youtubeChanel,
        String hobby) {
}
