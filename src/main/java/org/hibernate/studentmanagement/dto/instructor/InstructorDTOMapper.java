package org.hibernate.studentmanagement.dto.instructor;

import org.hibernate.studentmanagement.entity.Instructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InstructorDTOMapper implements Function<Instructor, InstructorDTO> {

    @Override
    public InstructorDTO apply(Instructor instructor) {

        return new InstructorDTO
                (instructor.getFirstName(),
                        instructor.getLastName(),
                        instructor.getEmail(),
                        instructor.getInstructorDetail().getYoutubeChanel(),
                        instructor.getInstructorDetail().getHobby());

    }
}
