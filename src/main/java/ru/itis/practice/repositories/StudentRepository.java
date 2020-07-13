package ru.itis.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.practice.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
