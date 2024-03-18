//package com.openclassrooms.mddapi.models;
//
//import java.sql.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//@Entity
//public class Comment {
//	
//	@Id
//	@GeneratedValue
//	@Column
//	private Long id;
//	
//	@Column
//	private String contenu;
//	
//	@Column
//	private Date create_at;
//	
//	@ManyToOne
//	@JoinColumn
//	private User userEntity;
//	
//	@ManyToOne
//	@JoinColumn
//	private Articles articleEntity;
//
//}
