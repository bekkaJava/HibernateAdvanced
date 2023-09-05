package org.hibernate.studentmanagement.service.instructor;

import org.hibernate.studentmanagement.dto.instructor.InstructorDTO;

import java.util.List;

public interface InstructorService {
    List<InstructorDTO> findAllInstructor();

    InstructorDTO findById(Integer id);

    InstructorDTO findByName(String name);

    InstructorDTO findByLastNameIgnoreCase(String name);

    InstructorDTO updateInstructor(Integer id, InstructorDTO instructor);

    void deleteInstructorById(Integer instructorId);

    void addInstructorWithDetails(InstructorDTO instructorDTO);
}
