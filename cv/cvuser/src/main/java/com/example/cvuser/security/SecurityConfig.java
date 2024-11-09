package com.example.cvuser.security; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/", "/register", "/login").permitAll() 
                .anyRequest().authenticated() 
                .and()
            .csrf().disable() 
            .logout()
                .logoutUrl("/logout") 
                .logoutSuccessUrl("/login") 
                .invalidateHttpSession(true)
                .permitAll();
        return http.build();
    }
}
