package ru.itis.practice.services;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.practice.models.User;
import ru.itis.practice.repositories.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> isValid(User user) {
		Optional<User> userCandidate = findByEmail(user.getEmail());
		if (userCandidate.isPresent()) {
			if (passwordEncoder.matches(user.getPassHash(), userCandidate.get().getPassHash())) {
				return userCandidate;
			}
		}
		return Optional.empty();
	}

	@Override
	public User save(User user) {
		user.setPassHash(passwordEncoder.encode(user.getPassHash()));
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		try {
			Optional<User> userCandidate = userRepository.findById(id);
			if (userCandidate.isPresent()) {
				return userCandidate.get();
			}
			throw new EmptyResultDataAccessException(-1);
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("No user found!");
		}
	}


}
