package com.openclassrooms.mddapi.dto.request;

import com.openclassrooms.mddapi.models.Theme;

public class SubscriptionRequest {
//    private Long idUser;

    private Theme theme;

    public SubscriptionRequest() {
    }

    public SubscriptionRequest(Theme theme) {
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
