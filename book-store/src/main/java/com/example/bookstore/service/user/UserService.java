package com.example.bookstore.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bookstore.dto.UserDto;
import com.example.bookstore.exception.NotFoundException;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserDto createUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = this.userRepository.save(user);
		 
		return UserDto.builder()
				.id(savedUser.getId())
				.userName(savedUser.getUserName())
				.email(savedUser.getEmail())
				.role(savedUser.getRole())
				.build();
		
	}
	
	public User findUserByUserName(String userName) {
		//return this.userRepository.findByUserName(userName).orElseThrow(() -> new NotFoundException("Requested user not found"));
		 return this.userRepository.findByUserName(userName);
	}
	
	public UserDto getUser(String userName) throws Exception {
		User user = findUserByUserName(userName);
		if(user == null) {
			throw new NotFoundException("User not found");
		}
		return UserDto.builder()
				.id(user.getId())
				.email(user.getEmail())
				.userName(user.getUserName())
				.role(user.getRole())
				.build();
	}

}
