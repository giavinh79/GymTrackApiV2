package com.gymtrack.api.feature.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @GetMapping
    public ResponseEntity getHealthCheck() {
        return new ResponseEntity<>("GymTrackApi",
                HttpStatus.OK);
    }

    @GetMapping("database")
    public ResponseEntity getDatabaseHealthCheck() {
        return new ResponseEntity<>("GymTrackApi",
                HttpStatus.OK);
    }
}
