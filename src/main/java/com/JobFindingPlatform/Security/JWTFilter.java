package com.JobFindingPlatform.Security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.JobFindingPlatform.Entity.User;
import com.JobFindingPlatform.Repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.validToken(token)) {
                String email = jwtUtil.extractUserEmail(token);
                User user = userRepository.findByUserEmail(email).orElse(null);

                if (user != null) {
                	UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                		    user,
                		    null,
                		    List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                		);
                		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                		SecurityContextHolder.getContext().setAuthentication(authToken);


                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
