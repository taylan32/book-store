package com.example.bookstore.service.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.bookstore.dto.LoginRequest;
import com.example.bookstore.dto.RegisterRequest;
import com.example.bookstore.dto.TokenResponseDto;
import com.example.bookstore.dto.UserDto;
import com.example.bookstore.exception.BusinessException;
import com.example.bookstore.exception.NotFoundException;
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

	public TokenResponseDto login(LoginRequest request) throws Exception {
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), (request.getPassword())));
			return TokenResponseDto.builder()
					.accessToken(tokenGenerator.generateToken(auth))
					.user(this.userService.getUser(request.getUserName()))
					.build();
		} catch(Exception exception) {
			throw new NotFoundException("Username or password is incorrect");
		}
	}
	
	public UserDto register(RegisterRequest request) throws Exception{
		if(checkIfUserNameInUse(request.getUserName())) {
			throw new BusinessException("Username already in use");
		}
		User user = User.builder()
				.userName(request.getUserName())
				.password(request.getPassword())
				.email(request.getEmail())
				.role(Role.USER) // intially role is USER
				.build();
		return this.userService.createUser(user);
		
	}
	
	public String getLoggedInUsername() {
		return ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}
	
	private boolean checkIfUserNameInUse(String username) {
		User user = this.userService.findUserByUserName(username);
		if(user == null) {
			return false;
		}
		return true;
	}

}
