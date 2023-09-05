package org.hibernate.studentmanagement.rest;

import org.hibernate.studentmanagement.dto.student.StudentDTO;
import org.hibernate.studentmanagement.entity.Student;
import org.hibernate.studentmanagement.service.student.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v3/student")
public class StudentRestController {
    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findStudentById(@PathVariable Integer id) {

        StudentDTO student = studentService.findStudentById(id);

        return ResponseEntity.ok(student);

    }

    @GetMapping("/")
    public ResponseEntity<List<StudentDTO>> findAllStudent() {

        List<StudentDTO> students = studentService.findAllStudent();

        return ResponseEntity.ok(students);

    }
    @GetMapping("/selectAllStudentsByCourseId")
    public ResponseEntity<List<String>> selectAllStudentsByCourseId(@RequestParam Integer courseId) {

        List<String> students = studentService.selectAllStudentsByCourseId(courseId);


        return ResponseEntity.ok(students);
    }




    @PostMapping("/")
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {

        studentService.addStudent(student);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer id, @RequestBody Student student) {

        StudentDTO updatedStudent = studentService.updateStudent(id, student);

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Integer id) {

        studentService.deleteStudentById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{studentId}/{courseId}")
    public ResponseEntity<Void> addCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {

        studentService.addCourse(studentId, courseId);

        return ResponseEntity.noContent().build();
    }
}
