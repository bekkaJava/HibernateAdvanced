package org.hibernate.studentmanagement.rest;

import org.hibernate.studentmanagement.dto.course.CourseDTO;
import org.hibernate.studentmanagement.entity.Course;
import org.hibernate.studentmanagement.service.course.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/course")
public class CourseRestController {

    private final CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findCourseById(@PathVariable Integer id) {

        CourseDTO course = courseService.findCourseById(id);

        return ResponseEntity.ok(course);

    }


    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> findAllCourses() {

        List<CourseDTO> courses = courseService.findAllCourses();

        return ResponseEntity.ok(courses);

    }

    @GetMapping("/findCoursesByInstructorId/{instructorId}")
    public ResponseEntity<List<String>> findCoursesByInstructorId(@PathVariable Integer instructorId) {

        List<String> courses = courseService.findCoursesByInstructorId(instructorId);

        return ResponseEntity.ok(courses);

    }


    @GetMapping("/findCourseByTitle")
    public ResponseEntity<CourseDTO> findCourseByTitle(@RequestParam String title) {

        CourseDTO courseDTO = courseService.findCourseByTitle(title);

        return ResponseEntity.ok(courseDTO);


    }

    @GetMapping("/selectAllCoursesByStudentId")
    public ResponseEntity<List<String>> selectAllCoursesByStudentId(@RequestParam Integer studentId) {

        List<String> courses = courseService.selectAllCoursesByStudentId(studentId);

        return ResponseEntity.ok(courses);
    }



    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Integer courseId, @RequestBody CourseDTO course) {

        CourseDTO updatedCourse = courseService.updateCourse(courseId, course);

        return ResponseEntity.ok(updatedCourse);

    }


    @PostMapping("/{instructorId}")
    public ResponseEntity<Void> addCourseWithInstructor(@PathVariable Integer instructorId, @RequestBody Course course) {

        courseService.addCourseWithInstructor(instructorId, course);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Integer id) {

        courseService.deleteCourseById(id);

        return ResponseEntity.noContent().build();

    }


    @PutMapping("{courseId}/{studentId}")
    public ResponseEntity<Void> addCourse(@PathVariable Integer courseId, @PathVariable Integer studentId) {

        courseService.addStudent(courseId, studentId);

        return ResponseEntity.noContent().build();
    }

}
