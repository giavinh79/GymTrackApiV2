package com.gymtrack.api.runner;

import com.gymtrack.api.platform.firebase.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class AppStartupRunner implements ApplicationRunner {
    private final FirebaseService firebaseService;

    @Autowired
    public AppStartupRunner(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @Override
    public void run(ApplicationArguments args) {
        // add env feature flag for being able to control whether we initialize a certain service or not
        firebaseService.initialize();
    }
}