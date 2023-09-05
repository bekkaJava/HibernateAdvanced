package org.hibernate.studentmanagement.dao;

import org.hibernate.studentmanagement.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    Optional<Instructor> findByLastNameEqualsIgnoreCase(String lastName);

    @Query(value = "select * from Instructor where first_name =?1 limit 1", nativeQuery = true)
    Optional<Instructor> findByFirstName(String name);

}
