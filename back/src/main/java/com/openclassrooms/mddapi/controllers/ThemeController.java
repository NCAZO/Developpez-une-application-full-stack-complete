package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.response.ThemeResponse;
import com.openclassrooms.mddapi.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ThemeController {
	
	@Autowired
	private ThemeService themeService;

	@GetMapping("/getThemes")
	public ResponseEntity<List<ThemeResponse>> getThemes() {
		try {
			return ResponseEntity.ok(themeService.getThemes().getBody());
		} catch (Exception e) {
			return (ResponseEntity<List<ThemeResponse>>) ResponseEntity.notFound();
		}
	}
}
