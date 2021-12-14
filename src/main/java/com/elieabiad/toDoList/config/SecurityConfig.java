package com.elieabiad.toDoList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
/*    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll();//disabled csrf because when it was enabled I couldn't send put delete create requests
        http.headers().frameOptions().disable();//disabled headers to connect to H2 console: https://stackoverflow.com/questions/53395200/h2-console-is-not-showing-in-browser
    }*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
        http.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")));
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
        UserDetails user1 = userBuilder
                .username("elieabiad")
                .password("password")
                .roles("USER","ADMIN")
                .build();

        UserDetails user2 = userBuilder
                .username("lucienchemaly")
                .password("password")
                .roles("USER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
