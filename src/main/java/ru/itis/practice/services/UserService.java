package ru.itis.practice.services;

import ru.itis.practice.models.User;

import java.util.Optional;

public interface UserService {

	Optional<User> findByEmail(String email);

	Optional<User> isValid(User user);

	User save(User user);
}
