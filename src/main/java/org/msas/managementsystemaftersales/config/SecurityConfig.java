package org.msas.managementsystemaftersales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/bast/**").hasRole("OPS")
//                .requestMatchers("/api/service-schedules/**").hasAnyRole("OPS", "BISNIS")
//                .anyRequest().authenticated()
//            )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()
                )

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public InMemoryUserDetailsManager users() {
        return new InMemoryUserDetailsManager(
                User.withUsername("ops")
                        .password("{noop}ops123")
                        .roles("OPS")
                        .build(),
                User.withUsername("bisnis")
                        .password("{noop}bisnis123")
                        .roles("BISNIS")
                        .build()
        );
    }


}
