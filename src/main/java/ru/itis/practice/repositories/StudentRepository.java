package ru.itis.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.practice.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByUser_Email(String email);
    List<Student> findAllByOrderByUser_FullName();
    @Query("update Student s set s.description = :description WHERE s.user.id = :id")
    @Modifying
    void updateDescription(@Param("id") Long id, @Param("description") String description);
}
