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

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User createUser(String email) {
        LocalDateTime now = LocalDateTime.now(clock);

        User user = User.builder()
                .email(email)
                .updatedAt(now)
                .build();

        return userRepository.save(user);
    }
}
