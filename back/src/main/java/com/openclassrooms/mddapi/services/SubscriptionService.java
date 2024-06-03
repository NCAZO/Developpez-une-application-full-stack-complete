package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Subscription;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private ThemeService themeService;
    @Autowired
    private ThemeRepository themeRepository;

    public ResponseEntity<List<Subscription>> getSubscriptions() {
        User user = authService.getMe();
        List<Subscription> subscriptions = subscriptionRepository.findAllByIdUser(user.getId());
        return ResponseEntity.ok(subscriptions);
    }

    public Subscription subscribeTheme(Long idTheme) {

        User user = authService.getMe();

        Long time = Date.from(Instant.now()).getTime();

        Theme theme = new Theme();
        theme.setId(idTheme);
        Subscription subscription = new Subscription(user.getId(), theme, new Date(time));
        return subscriptionRepository.save(subscription);
    }

    public void unSubscribe(Subscription newSubscription) {
        subscriptionRepository.delete(newSubscription);
    }

//    public ResponseEntity<List<Subscription>> isAlreadySub() {
////        User user = authService.getMe();
////        List<Subscription> subscriptions = subscriptionRepository.findAllByIdUser(user.getId());
////        return ResponseEntity.ok(subscriptions);
//        ResponseEntity<List<ThemeResponse>> listTheme = themeService.getThemes();
//    }
}
