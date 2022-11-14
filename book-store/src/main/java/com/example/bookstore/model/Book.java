package com.example.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@Builder
public class Book extends BaseEntity{

	private String title;
	
	private String authorName;
	
	@Enumerated(value = EnumType.STRING)
	private BookStatus bookStatus;
	
	private String publisher;
	
	private Integer lastPageNumber;
	private Integer totalPage;
	
	@OneToOne()
	private Image image;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;
	
	private Long userId;
	
}
