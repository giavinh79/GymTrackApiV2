package com.gymtrack.api.runner;

import com.gymtrack.api.platform.firebase.FirebaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {
    private final FirebaseService firebaseService;

    @Autowired
    public AppStartupRunner(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @Override
    public void run(ApplicationArguments args) {
        firebaseService.initialize();
    }
}