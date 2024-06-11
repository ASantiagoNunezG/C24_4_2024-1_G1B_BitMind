package com.backend.bitmind.Config;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // Configuring HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth.requestMatchers("/publicaciones/**","/anuncios/**","/foros/**","/auth/welcome", "/auth/addNewUser", "/auth/generateToken").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/user/**").authenticated())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/admin/**").authenticated())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build()
                ;
    }
}