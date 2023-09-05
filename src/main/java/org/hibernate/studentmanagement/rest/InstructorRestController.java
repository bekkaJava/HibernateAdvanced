package org.hibernate.studentmanagement.rest;

import org.hibernate.studentmanagement.dto.instructor.InstructorDTO;
import org.hibernate.studentmanagement.service.instructor.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructor")
public class InstructorRestController {

    private final InstructorService instructorService;

    public InstructorRestController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }


    @GetMapping("/findAllInstructors")
    public ResponseEntity<List<InstructorDTO>> findAllInstructor() {

        List<InstructorDTO> instructors = instructorService.findAllInstructor();

        return ResponseEntity.ok(instructors);

    }


    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> findInstructorById(@PathVariable Integer id) {

        InstructorDTO instructor = instructorService.findById(id);

        return ResponseEntity.ok(instructor);

    }


    @GetMapping("/")
    public ResponseEntity<InstructorDTO> findInstructorByName(@RequestParam String name) {

        InstructorDTO instructor = instructorService.findByName(name);

        return ResponseEntity.ok(instructor);

    }

    @GetMapping("/findInstructorByLastName")
    public ResponseEntity<InstructorDTO> findInstructorByLastName(@RequestParam String lastName) {

        InstructorDTO instructor = instructorService.findByLastNameIgnoreCase(lastName);

        return ResponseEntity.ok(instructor);

    }


    @PostMapping("/")
    public ResponseEntity<Void> addInstructor(@RequestBody InstructorDTO instructorDTO) {

        instructorService.addInstructorWithDetails(instructorDTO);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable Integer id,
                                                          @RequestBody InstructorDTO updatedInstructor) {

        InstructorDTO theInstructor = instructorService.updateInstructor(id, updatedInstructor);

        return ResponseEntity.ok(theInstructor);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructorById(@PathVariable Integer id) {

        instructorService.deleteInstructorById(id);

        return ResponseEntity.noContent().build();
    }


}
