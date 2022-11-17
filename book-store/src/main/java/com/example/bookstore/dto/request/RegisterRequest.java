package com.example.bookstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	@NotBlank(message = "Username is not allowed to be empty")
	private String userName;
	
	@NotBlank(message = "Password is not allowed to be empty")
	private String password;
	
	@Email(message = "Invalid email")
	@NotBlank(message = "Email is not allowed to be empty")
	private String email;

}
