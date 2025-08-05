package com.kingjnr.Application.config;

import com.kingjnr.Application.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

       return http.
                csrf(c -> c.disable()).
                httpBasic(Customizer.withDefaults()).
                authorizeHttpRequests(auth-> auth.requestMatchers("/register","/login","load").
                permitAll().
                requestMatchers("/admin/register").hasRole("ADMIN").anyRequest().authenticated()).
                sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                build();
    }


    @Bean
    public DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsServiceImpl);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        return provider;
    }


    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }



}
