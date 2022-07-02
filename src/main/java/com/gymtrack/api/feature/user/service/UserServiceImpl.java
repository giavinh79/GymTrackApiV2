package com.gymtrack.api.feature.user.service;

import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.user.model.User;
import com.gymtrack.api.feature.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final Clock clock = Clock.systemUTC();

    public User getUserByFirebaseId(String firebaseId) {
        return userRepository.findByFirebaseId(firebaseId).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User createUser(String email, String firebaseId) {
        LocalDateTime now = LocalDateTime.now(clock);

        User user = User.builder()
                .email(email)
                .firebaseId(firebaseId)
                .updatedAt(now)
                .build();

        return userRepository.save(user);
    }
}
