package ru.itis.practice.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.practice.models.User;
import ru.itis.practice.repositories.UserRepository;

import java.util.Optional;

@Component(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByEmail(email);
		if (userOptional.isPresent()) {
			System.out.println(userOptional.get());
			User user = userOptional.get();
			return new UserDetailsImpl(user);
		}
		throw new UsernameNotFoundException("User not found");
	}
}
