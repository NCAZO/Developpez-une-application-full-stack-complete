package com.openclassrooms.mddapi.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;

@Entity
public class Theme {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String title;
	
	@OneToMany(mappedBy = "theme")
	private List<Article> articles;
}
