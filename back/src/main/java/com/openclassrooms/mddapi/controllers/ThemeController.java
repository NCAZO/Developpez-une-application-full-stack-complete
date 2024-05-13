package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.services.ThemeService;

@RestController
public class ThemeController {
	
	@Autowired
	private ThemeService themeService;
	
	//Return list theme followed
//	public List<Theme> getThemes(){
//		return themeService.getFollowing();
//	}

}
