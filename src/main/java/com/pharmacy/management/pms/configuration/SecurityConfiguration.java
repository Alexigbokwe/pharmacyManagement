package com.pharmacy.management.pms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.RequiredArgsConstructor;

import static com.pharmacy.management.pms.configuration.Permission.ADMIN_CREATE;
import static com.pharmacy.management.pms.configuration.Permission.ADMIN_DELETE;
import static com.pharmacy.management.pms.configuration.Permission.ADMIN_READ;
import static com.pharmacy.management.pms.configuration.Permission.ADMIN_UPDATE;
import static com.pharmacy.management.pms.configuration.Permission.PHARMACIST_CREATE;
import static com.pharmacy.management.pms.configuration.Permission.PHARMACIST_DELETE;
import static com.pharmacy.management.pms.configuration.Permission.PHARMACIST_READ;
import static com.pharmacy.management.pms.configuration.Permission.PHARMACIST_UPDATE;
import static com.pharmacy.management.pms.configuration.Role.ADMIN;
import static com.pharmacy.management.pms.configuration.Role.PHARMACIST;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(request -> request
                // .anyRequest().permitAll()
                // Exclude this endpoint from authorization
                .requestMatchers("/api/v1/hello", "/api/v1/auth/**")
                .permitAll()
                // Define authorization rules
                .requestMatchers("/api/v1/pharmacist/**", "/api/v1/brands/**", "/api/v1/groups/**",
                        "/api/v1/medicines/**")
                .hasAnyRole(ADMIN.name(), PHARMACIST.name())

                .requestMatchers(GET, "/api/v1/pharmacist/**", "/api/v1/brands/**", "/api/v1/groups/**",
                        "/api/v1/medicines/**")
                .hasAnyAuthority(ADMIN_READ.name(), PHARMACIST_READ.name())

                .requestMatchers(POST, "/api/v1/pharmacist/**", "/api/v1/brands/**", "/api/v1/groups/**",
                        "/api/v1/medicines/**")
                .hasAnyAuthority(ADMIN_CREATE.name(), PHARMACIST_CREATE.name())

                .requestMatchers(PUT, "/api/v1/pharmacist/**", "/api/v1/brands/**", "/api/v1/groups/**",
                        "/api/v1/medicines/**")
                .hasAnyAuthority(ADMIN_UPDATE.name(), PHARMACIST_UPDATE.name())

                .requestMatchers(DELETE, "/api/v1/pharmacist/**", "/api/v1/brands/**", "/api/v1/groups/**",
                        "/api/v1/medicines/**")
                .hasAnyAuthority(ADMIN_DELETE.name(), PHARMACIST_DELETE.name())

                .anyRequest()
                .authenticated())

                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder.clearContext()));

        return http.build();
    }
}
