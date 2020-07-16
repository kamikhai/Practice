package ru.itis.practice.security.jwt.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.itis.practice.models.User;
import ru.itis.practice.security.details.UserDetailsImpl;
import ru.itis.practice.security.jwt.authentication.JwtAuthentication;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
	// секретный ключ, которым мы подписываем токен
	private String secret = "qwerty007";

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = authentication.getName();

		Claims claims;
		try {
			// выполняю парсинг токена со своим секретным ключом
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("Bad token");
		}
		// создаем UserDetails
		UserDetailsImpl userDetails = UserDetailsImpl.builder()
				.user(User.builder()
						.id(Long.parseLong(claims.get("sub", String.class)))
						.role(User.Role.valueOf(claims.get("role", String.class)))
						.fullName(claims.get("name", String.class))
						.email(claims.get("email", String.class))
						.build())
				.build();

		System.out.println(userDetails.getUser() + "2344");
		// аутентификация прошла успешно
		authentication.setAuthenticated(true);
		// положили в эту аутентификацию пользователя
		((JwtAuthentication) authentication).setUserDetails(userDetails);
		return authentication;
	}

	// проверяет, подходит ли текущий провайдер для этой аутентификации
	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthentication.class.equals(authentication);
	}
}

