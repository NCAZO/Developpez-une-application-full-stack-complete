/*
package com.openclassrooms.mddapi.models;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import jakarta.persistence.Id;


@Builder
@Entity(name = "article")
public class Article {

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Long Id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

//	@Column
	@CreationTimestamp
	@Column(name = "create_at")
	private Date create_at;
	
	@ManyToOne
	@JoinColumn(name = "theme_Id")
	private Theme theme;

	@ManyToOne
    @JoinColumn(name = "fk_id_user", updatable = false)
    private User user;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

	public Article() {

	}

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
*/
