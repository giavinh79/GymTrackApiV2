package com.gymtrack.api.filter.auth;

import com.google.firebase.auth.FirebaseAuthException;
import com.gymtrack.api.exception.AuthenticationException;
import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.user.model.User;
import com.gymtrack.api.feature.user.service.UserServiceImpl;
import com.gymtrack.api.platform.firebase.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirebaseAuth implements Auth {
    private final FirebaseService firebaseService;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public FirebaseAuth(UserServiceImpl userServiceImpl, FirebaseService firebaseService) {
        this.userServiceImpl = userServiceImpl;
        this.firebaseService = firebaseService;
    }

    public User authenticate(String jwtToken) throws AuthenticationException, NotFoundException {
        try {
            String userUid = firebaseService.authenticateFirebaseUser(jwtToken);
            User user = userServiceImpl.getUserByFirebaseId(userUid);
            return user;
        } catch (FirebaseAuthException ex) {
            throw new AuthenticationException(ex.getMessage());
        }
    }
}

