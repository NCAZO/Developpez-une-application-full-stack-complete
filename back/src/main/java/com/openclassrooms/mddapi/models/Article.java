package com.openclassrooms.mddapi.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;



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
	
	@ManyToOne
	@JoinColumn(name = "themeId")
	private Theme theme;

	@ManyToOne
    @JoinColumn(name = "fk_id_user", updatable = false)
    private User user;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
