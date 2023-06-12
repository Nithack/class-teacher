package com.project.classteacher.infra.http.config;

import com.project.classteacher.application.port.UserPort;
import com.project.classteacher.infra.http.config.filter.MySecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Autowired
    private UserPort userPort;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                        (authorize) -> {
                            authorize
                                    .requestMatchers(
                                            HttpMethod.POST,
                                            "/auth/login"
                                    ).permitAll()
                                    .requestMatchers(
                                            HttpMethod.POST,
                                            "/auth/register"
                                    ).permitAll()
                                    .requestMatchers(
                                            "/secretary/**"
                                    ).hasRole("SECRETARY")
                                    .requestMatchers(
                                            "/teacher/**"
                                    ).hasRole("TEACHER")
                                    .requestMatchers(
                                            "/v2/api-docs",
                                            "/v3/api-docs",
                                            "/v3/api-docs/**",
                                            "/swagger-resources",
                                            "/swagger-resources/**",
                                            "/configuration/ui",
                                            "/configuration/security",
                                            "/swagger-ui/**",
                                            "/swagger-ui/",
                                            "/webjars/**",
                                            "/swagger-ui.html"
                                    ).permitAll()
                                    .requestMatchers(
                                            "/api/v1/auth/**"
                                    ).permitAll()
                                    .anyRequest().authenticated();

                        }
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);

        http.addFilterBefore(new MySecurityFilter(userPort), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
