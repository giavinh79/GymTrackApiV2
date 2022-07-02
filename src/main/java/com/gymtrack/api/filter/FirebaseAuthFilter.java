package com.gymtrack.api.filter;

import com.google.firebase.auth.FirebaseAuthException;
import com.gymtrack.api.exception.AuthenticationException;
import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.user.model.User;
import com.gymtrack.api.feature.user.service.UserServiceImpl;
import com.gymtrack.api.platform.firebase.FirebaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(2)
@Slf4j
@Profile("!test")
public class FirebaseAuthFilter extends OncePerRequestFilter implements Filter {
    private final FirebaseService firebaseService;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public FirebaseAuthFilter(UserServiceImpl userServiceImpl, FirebaseService firebaseService) {
        this.userServiceImpl = userServiceImpl;
        this.firebaseService = firebaseService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException, AuthenticationException {
        String jwtToken = request.getParameter("token");

        try {
            String userUid = firebaseService.authenticateFirebaseUser(jwtToken);
            User user = userServiceImpl.getUserByFirebaseId(userUid);

            request.setAttribute("user", user);

            log.info("[SUCCESS] User authenticated - [userId={}, ip={}]", user.getId(), request.getRemoteAddr());
        } catch (FirebaseAuthException ex) {
            log.warn("[WARNING] User was unauthorized - [ip={}]", request.getRemoteAddr());
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials");
            return;
        } catch (NotFoundException ex) {
            log.warn("[WARNING] User was not found - [ip={}]", request.getRemoteAddr());
            response.sendError(HttpStatus.NOT_FOUND.value(), "User was not found");
            return;
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Configures filter above to only run on API routes (i.e. ignore public routes like actuator/health)
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        List<String> acceptedRegexPaths = Arrays.asList(
                "/actuator/.*",
                "/api/.*/auth/signup"
        );

        return acceptedRegexPaths.stream().anyMatch(path::matches);
    }
}

