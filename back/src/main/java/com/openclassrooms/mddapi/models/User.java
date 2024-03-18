package com.openclassrooms.mddapi.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.lang.NonNull;

@Entity(name = "user")
@Table(name = "user", uniqueConstraints = {
	    @UniqueConstraint(columnNames = "email")
	})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NonNull
	private String nom;
	
	@Column
	@NonNull
	private String email;
	
	@Column
	@NonNull
	private String password;
	
//	@OneToMany(
//			mappedBy = "user",
//			cascade = CascadeType.ALL,
//			fetch = FetchType.LAZY)
//	private List<Subscription> themes;
}
