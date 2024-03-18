//package com.openclassrooms.mddapi.models;
//
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
//@Entity
//public class Theme {
//	
//	@Id
//	@GeneratedValue
//	private Long id;
//	
//	@Column
//	private String title;
//	
//	@Column
//	private String description;
//	
//	@OneToMany(
//			mappedBy = "theme",
//			cascade = CascadeType.ALL,
//			fetch = FetchType.LAZY)
//	private List<Subscription> subscriptionList;
//	
//	@OneToMany(
//			mappedBy = "id",
//			cascade = CascadeType.ALL,
//			orphanRemoval = true)
//	private List<Articles> articleEntity;
//}
