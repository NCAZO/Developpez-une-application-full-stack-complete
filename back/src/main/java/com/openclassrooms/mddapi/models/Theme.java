package com.openclassrooms.mddapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Theme {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

//	@OneToMany(mappedBy = "theme")
//	private List<Article> articles;

    /*@OneToMany(mappedBy = "theme")
    private Set<Subscription> subscriptions = new HashSet<>();*/

    public Theme() {

    }

    public Theme(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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

    /*public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }*/
}
