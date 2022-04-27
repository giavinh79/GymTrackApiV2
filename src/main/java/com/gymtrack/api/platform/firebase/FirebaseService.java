package com.gymtrack.api.platform.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import com.gymtrack.api.exception.AuthenticationException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Google's Firebase Admin SDK
@Component
@Slf4j
@Profile("!test")
public class FirebaseService {
    public void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .setDatabaseUrl("https://gymtrack-e6a77.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            log.info("Initialized Google Firebase successfully");
        } catch (IOException e) {
            System.out.println("ERROR INITIALIZING FIREBASE - " + e.getMessage());
            System.exit(1);
        }
    }

    public String authenticateFirebaseUser(String token) throws AuthenticationException, FirebaseAuthException {
        if (token == null) {
            throw new AuthenticationException("No token provided");
        }

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        return decodedToken.getUid();
    }
}
