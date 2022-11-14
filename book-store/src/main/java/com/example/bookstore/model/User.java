package com.example.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseEntity{

	
	
}
