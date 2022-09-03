package com.gymtrack.api.context;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
/*
 This is forwarded with every request from the auth filter and is accessed with @RequestAttribute in the controller.
 To create this, we fetch the user from the UserRepository in the auth filter to ensure they exist. However, this
 hibernate session gets terminated by the time we use it at the service/controller layers therefore you can no longer
 lazy fetch any associations (like userRoutines) - https://stackoverflow.com/a/6175716

 Therefore, I created this UserContext to help differentiate it with User and pass along only information I want to.
 */
public class UserContext {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdAt;
}
