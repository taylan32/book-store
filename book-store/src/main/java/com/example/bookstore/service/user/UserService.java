package com.example.bookstore.service.user;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.dto.ErrorCode;
import com.example.bookstore.dto.UserDto;
import com.example.bookstore.exception.GenericException;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public User createUser(User user) {
		return this.userRepository.save(user);
	}

	public User findUserByUserName(String userName) {
		return this.userRepository.findByUserName(userName).orElseThrow(notFoundUser(HttpStatus.NOT_FOUND));
	}

	public UserDto getUser(String userName) {
		User user = findUserByUserName(userName);
		return UserDto.builder().id(user.getId()).userName(user.getUserName()).role(user.getRole()).build();
	}

	public UserDto findUserInContext() {
		final Authentication authentication = Optional
				.ofNullable(SecurityContextHolder.getContext().getAuthentication())
				.orElseThrow(notFoundUser(HttpStatus.NOT_FOUND));
		final UserDetails userDetails = Optional.ofNullable((UserDetails) authentication.getPrincipal())
				.orElseThrow(notFoundUser(HttpStatus.UNAUTHORIZED));
		return getUser(userDetails.getUsername());

	}

	public boolean existsByUserName(String userName) {
		return this.userRepository.existsByUserName(userName);
	}

	private static Supplier<GenericException> notFoundUser(HttpStatus httpStatus) {
		return () -> GenericException.builder().httpStatus(httpStatus).errorCode(ErrorCode.NOT_FOUND)
				.errorMessage("User not found").build();
	}

}
