package com.gymtrack.api.feature.user.routine.service;

import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.user.model.User;
import com.gymtrack.api.feature.user.repository.UserRepository;
import com.gymtrack.api.feature.user.routine.dto.UserRoutineDTO;
import com.gymtrack.api.feature.user.routine.mapper.UserRoutineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserRoutineServiceImpl {
    private final UserRepository userRepository;
    private final UserRoutineMapper userRoutineMapper;

    public List<UserRoutineDTO> getUsersRoutinesById(Integer userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User does not exist"));

        return user
                .getUserRoutines()
                .stream()
                .map((userRoutineMapper::routineToUserRoutineDTO))
                .toList();
    }
}
