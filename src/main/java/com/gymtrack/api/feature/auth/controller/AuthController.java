package com.gymtrack.api.feature.auth.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.gymtrack.api.feature.auth.dto.UserSignupDTO;
import com.gymtrack.api.feature.user.model.User;
import com.gymtrack.api.feature.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserServiceImpl userService;

    @Operation(summary = "Create a new user and sign them up for Firebase")
    @PostMapping("/signup")
    public User signup(@Valid @RequestBody UserSignupDTO userSignupDTO) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(userSignupDTO.email())
                .setEmailVerified(false)
                .setPassword(userSignupDTO.password())
                .setDisplayName(userSignupDTO.email())
                .setDisabled(false);

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        return userService.createUser(userRecord.getEmail(), userRecord.getUid());
    }
}
