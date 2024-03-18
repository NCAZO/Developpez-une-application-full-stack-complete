package com.openclassrooms.mddapi.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name = "article")
public class Article {

	@Id
	@GeneratedValue
	private Long Id;
	
	@Column
	private String title;
	
	@Column
	private String content;
	
	@Column
	private Date create_at;
	
//	@ManyToOne
//	@JoinColumn
//	private Theme theme;
	
//	@ManyToOne
//	@JoinColumn(name = "fk_id_user", updatable = false)
//	private User user;
//	
//	@OneToMany(
//			mappedBy = "articleEntity",
//			cascade = CascadeType.ALL
//			)
//	private List<Comment> comments;
}
