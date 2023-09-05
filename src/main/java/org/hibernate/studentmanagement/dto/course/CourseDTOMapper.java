package org.hibernate.studentmanagement.dto.course;

import org.hibernate.studentmanagement.entity.Course;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CourseDTOMapper implements Function<Course, CourseDTO> {

    @Override
    public CourseDTO apply(Course course) {

        return new CourseDTO(course.getTitle());
    }
}
