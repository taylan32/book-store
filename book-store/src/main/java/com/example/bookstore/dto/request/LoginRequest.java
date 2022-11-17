package com.example.bookstore.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NotBlank(message = "Username is not allowed to be empty")
	private String userName;
	
	@NotBlank(message = "Password is not allowed to be empty")
	private String password;
	
	
}
