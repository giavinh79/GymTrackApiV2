package com.gymtrack.api.feature.admin.controller;

import com.gymtrack.api.exception.ForbiddenException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/admin")
@Hidden
public class AdminController {
    @GetMapping("/user/{id}")
    public String updateUser(@PathVariable String id) {
        // TODO - validate user credentials and check if they are a super-admin

        // placeholder for above TODO
        if (!id.equals("")) {
            throw new ForbiddenException();
        }

        log.info("[SUCCESS] Updated user with super-admin privileges - [userId={}]", id);
        return id;
    }
}
