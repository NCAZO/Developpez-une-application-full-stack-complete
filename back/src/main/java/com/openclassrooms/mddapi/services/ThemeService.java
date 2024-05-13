package com.openclassrooms.mddapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.User;

@Service
public class ThemeService {
	
//	@Autowired
//	private ThemeRepository themeRepository;

//	public Theme getById(Long Id) throws NotFoundException {
//		Optional<Theme> theme = themeRepository.findById(Id);
//
//		if(theme.isEmpty()) {
//			throw new NotFoundException();
//		}
//		return theme.get();
//	}
	
//	public List<Theme> getFollowing(){
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		
//		if(user == null) {
//	        throw new Exception("Utilisateur non authentifié !");			
//		}
//		
//		Theme theme = Theme.builder()
//				.articles(user.getFollowing().contains(theme))
//	}
	
}
