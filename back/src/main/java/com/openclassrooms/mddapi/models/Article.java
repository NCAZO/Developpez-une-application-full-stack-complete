package com.openclassrooms.mddapi.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content",
            columnDefinition = "TEXT")
//    @Column(name = "content")
    private String content;

    @Column(name = "created_at", updatable = false)
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "fk_id_user", updatable = false)
    private User user;

    public Article() {
    }

    public Article(Long id, String title, String content, Date created_at, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
