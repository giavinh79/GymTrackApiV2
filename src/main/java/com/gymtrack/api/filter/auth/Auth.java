package com.gymtrack.api.filter.auth;

import com.gymtrack.api.exception.AuthenticationException;
import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.user.model.User;

interface Auth {
    User authenticate(String jwtToken) throws AuthenticationException, NotFoundException;
}
