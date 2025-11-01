package com.JobFindingPlatform.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    	  .csrf(csrf -> csrf.disable())
    	  .authorizeHttpRequests(auth -> auth
    	      .requestMatchers("/api/auth/**").permitAll()
    	      .requestMatchers("/api/support/**").permitAll() // ✅ Add this line
    	      .requestMatchers("/ws-chat/**", "/topic/**", "/app/**", "/support/**").permitAll()
    	      .requestMatchers(
    	          "/style.css",
    	          "/auth/**",
    	          "/css/**",
    	          "/js/**",
    	          "/images/**",
    	          "/favicon.ico",
    	          "/dashboard/**"
    	      ).permitAll()
    	      .anyRequest().authenticated()
    	  )
    	  .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
