package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.response.ThemeResponse;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    public ResponseEntity<List<ThemeResponse>> getThemes() {
        List<Theme> themes = (List<Theme>) themeRepository.findAll();
        List<ThemeResponse> themeResponses = new ArrayList<>();
        for (Theme theme : themes) {
            themeResponses.add(new ThemeResponse(theme.getId(), theme.getTitle(), theme.getContent()));
        }
        return ResponseEntity.ok(themeResponses);
    }

    /*public Long findById(Long id) {
        return themeRepository.findById(id);
    }*/


}
