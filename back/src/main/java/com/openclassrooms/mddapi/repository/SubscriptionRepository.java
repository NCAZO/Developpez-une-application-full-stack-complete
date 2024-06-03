package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    List<Subscription> findAllByIdUser(Long userId);
}
