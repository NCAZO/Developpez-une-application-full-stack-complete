package com.openclassrooms.mddapi.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "article_id")
    private Long articleId;

//    @Column(name = "user_id")
//    private Long userId;

    @ManyToOne
    @JoinColumn(name = "fk_id_user", updatable = false)
    private User user;

    @Column(name = "created_at", updatable = false)
    private Date created_at;

    public Comment() {
    }

    public Comment(Long id, String content, Long articleId, User user, Date created_at) {
        this.id = id;
        this.content = content;
        this.articleId = articleId;
        this.user = user;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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
}
