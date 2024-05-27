package com.openclassrooms.mddapi.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "id_user")
    private Long idUser;

    @ManyToOne
    @JoinColumn(name = "id_theme")
    private Theme theme;

    @Column(name = "created_at")
    private Date created_at;

    public Subscription() {
    }

    public Subscription(Long idUser, Theme theme, Date created_at) {
        this.idUser = idUser;
        this.theme = theme;
        this.created_at = created_at;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
