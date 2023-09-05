package org.hibernate.studentmanagement.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Instructor")
@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @JoinColumn(name = "instructor_detail_id")
    @OneToOne(cascade = CascadeType.ALL)
    private InstructorDetail instructorDetail;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "instructor",
                                     cascade = {CascadeType.DETACH,
                                     CascadeType.MERGE,
                                     CascadeType.PERSIST,
                                     CascadeType.REFRESH})
    private List<Course> courses;


    public void addCourse(Course course) {

        if (courses == null) {
            courses = new ArrayList<>();
        }

        course.setInstructor(this);
        courses.add(course);
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
