package com.cafe.com.cafe.JWT;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

// video-2
@Component
public class JwtFilter extends OncePerRequestFilter {

    // v-2  	
    @Autowired
    private JwtUtil jwtUtil;

    // v-2
    @Autowired
    private CustomerUsersDetailsService service;

    // v-2
    Claims claims = null;
    private String userName = null;

    // v-2
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // if the api requests are for login, signup, or forgotPassword, continue, otherwise required authentication
        if (httpServletRequest.getServletPath().matches("/user/login|/user/signup|/user/forgotPassword")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
        else {
            String authorizationHeader = httpServletRequest.getHeader("Authorization");
            String token = null;

            // authorization is successful
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                userName = jwtUtil.extractUsername(token);
                claims = jwtUtil.extractAllClaims(token);
            }

            // user is valid
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = service.loadUserByUsername(userName);
                if (jwtUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(
                            // converts HttpServletRequest into an instance of the WebAuthenticationDetails class
                            // HttpServletRequest = parsed raw HTTP data
                            new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                    );
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            // continue filtration
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    // v-2
    public boolean isAdmin() {
        return "admin".equalsIgnoreCase((String) claims.get("role"));
    }

    // v-2
    public boolean isUser() {
        return "user".equalsIgnoreCase((String) claims.get("role"));
    }

    public String getCurrentUser() {
        return userName;
    }
}
