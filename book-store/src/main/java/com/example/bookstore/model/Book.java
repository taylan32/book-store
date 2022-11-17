package com.example.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name  ="author_name", nullable = false)
	private String authorName;
	
	@Enumerated(value = EnumType.STRING)
	private BookStatus bookStatus;
	
	@Column(name = "publisher", nullable = false)
	private String publisher;
	
	@Column(name = "last_page_number", nullable = false)
	private Integer lastPageNumber;
	
	@Column(name = "total_page", nullable = false)
	private Integer totalPage;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Image image;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;
	
	@Column(name = "user_id", nullable = false)
	private Long userId;
	
}
