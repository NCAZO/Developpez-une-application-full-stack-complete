package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.Subscription;
import com.openclassrooms.mddapi.services.AuthService;
import com.openclassrooms.mddapi.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private AuthService authService;

    @GetMapping("/getSubscriptions")
    public ResponseEntity<List<Subscription>> getSubscriptions() {
        try {
            return ResponseEntity.ok(subscriptionService.getSubscriptions().getBody());
        } catch (Exception e) {
            return (ResponseEntity<List<Subscription>>) ResponseEntity.notFound();
        }
    }

    @PostMapping("/subscribeTheme/{idTheme}")
    public ResponseEntity<Subscription> subscribeTheme(@PathVariable Long idTheme) {
        try {
            Subscription subResponse = subscriptionService.subscribeTheme(idTheme);
            return new ResponseEntity<>(subResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/unSubscribe/{idSub}")
    public ResponseEntity<Subscription> unSubscribe(@PathVariable Long idSub) {
        try {
            Subscription newSubscription = new Subscription();
            newSubscription.setId(idSub);

            subscriptionService.unSubscribe(newSubscription);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/isAlreadySub")
//    public ResponseEntity<> isAlreadySub() {
//        try {
//            return ResponseEntity.ok(subscriptionService.isAlreadySub().getBody());
//        } catch (Exception e) {
//            return (ResponseEntity<List<Subscription>>) ResponseEntity.notFound();
//        }
//    }
}
