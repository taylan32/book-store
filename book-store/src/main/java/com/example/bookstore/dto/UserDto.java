package com.example.bookstore.dto;


import com.example.bookstore.model.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

	private Long id;
	private String userName;
	private Role role;
	private String email;
}
