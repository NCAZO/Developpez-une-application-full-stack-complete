package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ArticleRequest {

    //	@NotNull
	private Long themeId;
	
	@NotBlank
    private String title;
	
	@NotBlank
	private String content;

    public ArticleRequest(String title, Long themeId, String content) {
        this.title = title;
        this.themeId = themeId;
        this.content = content;
    }

    public ArticleRequest() {
    }

    public @NotNull Long getThemeId() {
		return themeId;
	}

    public void setThemeId(@NotNull Long themeId) {
		this.themeId = themeId;
	}

    public @NotBlank String getTitle() {
        return title;
	}

    public void setTitle(@NotBlank String title) {
        this.title = title;
	}

    public @NotBlank String getContent() {
		return content;
	}

    public void setContent(@NotBlank String content) {
		this.content = content;
	}
}
