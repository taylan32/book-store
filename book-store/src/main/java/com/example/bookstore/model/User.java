package com.example.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseEntity {

	@Column(name = "user_name", unique = true, nullable = false)
	private String userName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String email;
	
	
}
