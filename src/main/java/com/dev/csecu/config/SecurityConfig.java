package com.dev.csecu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login","/performlogin","/upEventShow",
                                "/userSignup","/memberSignForm","/showVision","/showBatch",
                                "/showContact","/showHistory","/resources/**",
                                "/css/**", "/images/**", "/js/**", "/**").permitAll() // Allow public access to these URLs
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")  // Custom login page
                        //.loginProcessingUrl("/perform_login")  // Handle form submission to this URL
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );
        return http.build();
    }

}
