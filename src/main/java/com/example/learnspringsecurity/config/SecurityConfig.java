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
                .requestMatchers("/about", "/logout").permitAll()
        ).formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("mobile")
                .defaultSuccessUrl("/dashboard",true)//for smart routing use false
                //true will force user to the dashboard anyway
                .failureUrl("/login?error")
        ).logout(logout->logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID","moneRakhio1Mash")
        ).rememberMe(form -> form
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("moneRakhio1Mash")//changed remember me cookie name
                .tokenValiditySeconds(2592000)
        )


        ;
        return http.build();
    }
}
