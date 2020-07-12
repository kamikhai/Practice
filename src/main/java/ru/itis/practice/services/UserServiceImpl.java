package ru.itis.practice.services;

import lombok.AllArgsConstructor;
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


}
