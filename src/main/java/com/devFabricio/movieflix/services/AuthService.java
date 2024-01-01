package com.devFabricio.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devFabricio.movieflix.entities.User;
import com.devFabricio.movieflix.repositories.UserRepository;
import com.devFabricio.movieflix.services.exceptions.ForbiddenException;
import com.devFabricio.movieflix.services.exceptions.UnauthorizedException;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	

	@Transactional(readOnly = true)
	public User authenticated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findByEmail(username);
		} catch (Exception e) {
			throw new UnauthorizedException("Inavlid user");
		}
	}

	public void validateSelfOrAdmin(Long userId) {
		User user = authenticated();
		if (!user.getId().equals(userId) && !user.hasRole("Role_ADMIN")) {
			throw new ForbiddenException("Access danied");
		}

	}

}
