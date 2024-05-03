package com.openclassrooms.mddapi.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import jakarta.persistence.Id;

@Builder
@Entity
public class Theme {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@OneToMany(mappedBy = "theme")
	private List<Article> articles;
}
