package com.example.learnspringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        IO.println("....config......");
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/login").anonymous()//for those who are not logged in
                .requestMatchers("/dashboard", "/").authenticated()
                .requestMatchers("/admin", "/admin/*", "/admin/**").denyAll()
                .requestMatchers("/*.css").permitAll()
                .requestMatchers("/about").permitAll()
        ).formLogin(Customizer.withDefaults()
        ).logout(Customizer.withDefaults()
        ).rememberMe(Customizer.withDefaults())
        ;
        return http.build();
    }
}
