package com.eventapp.event_management_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.eventapp.event_management_backend.domain.User;

import java.util.Optional;
import java.util.UUID;
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
