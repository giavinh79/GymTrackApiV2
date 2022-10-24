package com.gymtrack.api.feature.user.routine.service;

import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine.repository.RoutineRepository;
import com.gymtrack.api.feature.user.model.User;
import com.gymtrack.api.feature.user.routine.dto.UserRoutineRequestDTO;
import com.gymtrack.api.feature.user.routine.dto.UserRoutineResponseDTO;
import com.gymtrack.api.feature.user.routine.mapper.UserRoutineMapper;
import com.gymtrack.api.feature.user.routine.repository.UserRoutineRepository;
import com.gymtrack.api.feature.user_routine.model.UserRoutine;
import com.gymtrack.api.feature.user_routine.model.UserRoutineId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserRoutineServiceImpl {
    private final RoutineRepository routineRepository;
    private final UserRoutineRepository userRoutineRepository;

    private final UserRoutineMapper userRoutineMapper;

    private final Clock clock = Clock.systemUTC();

    private void removeExistingSelectedRoutineIfExists(Integer userId) {
        userRoutineRepository
                .findUserRoutineByUserIdEqualsAndIsSelectedTrue(userId)
                .ifPresent(selectedUserRoutine -> {
                    selectedUserRoutine.setIsSelected(false);
                    userRoutineRepository.save(selectedUserRoutine);
                });
    }

    @Transactional
    public UserRoutineResponseDTO createUserRoutine(User user, UserRoutineRequestDTO userRoutineRequestDTO) {
        Routine routineToCreate = Routine.builder()
                .creatorId(user.getId())
                .name(userRoutineRequestDTO.name())
                .description((userRoutineRequestDTO.description()))
                .imageId(userRoutineRequestDTO.imageId())
                .numTimesCopied(0)
                .routineExercises(Collections.emptyList())
                .build();

        Routine savedRoutine = routineRepository.save(routineToCreate);

        removeExistingSelectedRoutineIfExists(user.getId());

        UserRoutine userRoutineToCreate = UserRoutine.builder()
                .id(new UserRoutineId(user.getId(), savedRoutine.getId()))
                .user(user)
                .routine(savedRoutine)
                .isSelected(true)
                .updatedAt(LocalDateTime.now(clock))
                .build();

        UserRoutine createdUserRoutine = userRoutineRepository.save(userRoutineToCreate);
        return userRoutineMapper.userRoutineToUserRoutineDTO(createdUserRoutine);
    }

    public List<UserRoutineResponseDTO> getUsersRoutinesById(User user) {
        return userRoutineRepository.findAllByUserEquals(user)
                .stream()
                .map((userRoutineMapper::userRoutineToUserRoutineDTO))
                .toList();
    }

    public UserRoutineResponseDTO getSelectedUserRoutine(User user) {
        UserRoutine selectedUserRoutine = userRoutineRepository
                .findUserRoutineByUserIdEqualsAndIsSelectedTrue(user.getId())
                .orElse(null);

        return selectedUserRoutine == null
                ? null
                : userRoutineMapper.userRoutineToUserRoutineDTO(selectedUserRoutine);
    }
}
