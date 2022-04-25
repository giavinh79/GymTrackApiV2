package com.gymtrack.api.filter;

import com.google.firebase.auth.FirebaseAuthException;
import com.gymtrack.api.exception.AuthenticationException;
import com.gymtrack.api.feature.user.model.User;
import com.gymtrack.api.feature.user.service.UserServiceImpl;
import com.gymtrack.api.platform.firebase.FirebaseService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
@Slf4j
public class FirebaseAuthFilter implements Filter {
    private final FirebaseService firebaseService;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public FirebaseAuthFilter(UserServiceImpl userServiceImpl, FirebaseService firebaseService) {
        this.userServiceImpl = userServiceImpl;
        this.firebaseService = firebaseService;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException, AuthenticationException {
        HttpServletRequest req = (HttpServletRequest) request;

        String jwtToken = request.getParameter("token");

        try {
            String userUid = firebaseService.authenticateFirebaseUser(jwtToken);
            User user = userServiceImpl.getUserByFirebaseId(userUid);

            request.setAttribute("user", user);

            log.info("Authenticating user for endpoint: {}", req.getRequestURI());
        } catch(FirebaseAuthException ex) {
            throw new AuthenticationException("Invalid Firebase credentials.");
        }

        chain.doFilter(request, response);
    }

    /**
     * Configures filter above to only run on API routes (i.e. ignore public routes like actuator/health)
     */
    @Bean
    FilterRegistrationBean<FirebaseAuthFilter> firebaseAuthFilterRegistration () {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new FirebaseAuthFilter(userServiceImpl, firebaseService));
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}

