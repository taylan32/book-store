package com.example.bookstore.service.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bookstore.dto.ErrorCode;
import com.example.bookstore.dto.TokenResponseDto;
import com.example.bookstore.dto.UserDto;
import com.example.bookstore.dto.request.LoginRequest;
import com.example.bookstore.dto.request.RegisterRequest;
import com.example.bookstore.exception.GenericException;
import com.example.bookstore.model.Role;
import com.example.bookstore.model.User;
import com.example.bookstore.service.user.UserService;
import com.example.bookstore.utils.TokenGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserService userService;
	private final TokenGenerator tokenGenerator;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder encoder;

	public TokenResponseDto login(LoginRequest request) {
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), (request.getPassword())));
			return TokenResponseDto.builder()
					.accessToken(tokenGenerator.generateToken(auth))
					.user(this.userService.getUser(request.getUserName()))
					.build();
		} catch(Exception exception) {
			throw GenericException.builder()
				.httpStatus(HttpStatus.NOT_FOUND)
				.errorCode(ErrorCode.NOT_FOUND)
				.errorMessage("Invalid username or password")
				.build();
		}
	}
	
	public UserDto register(RegisterRequest request) {
		boolean userIsAlreadyRegistered = this.userService.existsByUserName(request.getUserName());
		if(userIsAlreadyRegistered) {
			throw GenericException.builder()
			.httpStatus(HttpStatus.FOUND)
			.errorCode(ErrorCode.FOUND)
			.errorMessage("Username" + request.getUserName() + "is already used")
			.build();
		}
		
		User user = User.builder()
				.userName(request.getUserName())
				.password(encoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		
		User savedUser = this.userService.createUser(user);
		return UserDto.builder()
				.id(savedUser.getId())
				.userName(savedUser.getUserName())
				.role(savedUser.getRole())
				.build();
	}
	
	public String getLoggedInUsername() {
		return ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}
	


}
