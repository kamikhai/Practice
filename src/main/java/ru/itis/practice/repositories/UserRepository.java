package ru.itis.practice.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.practice.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
