package com.openclassrooms.mddapi.dto.response;

import com.openclassrooms.mddapi.models.User;

import java.util.Date;

public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private Date created_at;
    private User user;

    public ArticleResponse() {
    }

    public ArticleResponse(Long id, User user, String content, String title, Date created_at) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.title = title;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
