package com.elieabiad.toDoList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 /*   protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        http.headers().frameOptions().disable();//disabled headers to connect to H2 console: https://stackoverflow.com/questions/53395200/h2-console-is-not-showing-in-browser
    }*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));//sends the csrf token cookie
        //http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")));//to send a new csrf token after logout
        http.authorizeRequests().antMatchers("/**").permitAll()
                .and().csrf().ignoringAntMatchers("/h2/**")
                .and().headers().frameOptions().sameOrigin();//to allow h2 console to work without csrf token
/*        http
                .authorizeRequests((authorizeRequests) ->
                        authorizeRequests
                                .antMatchers("/users/**").hasAnyRole("ADMIN","USER")
                );*/
        //http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
        UserDetails user1 = userBuilder
                .username("elieabiad")
                .password("password")
                .roles("ADMIN","USER")
                .build();

        UserDetails user2 = userBuilder
                .username("lucienchemaly")
                .password("password")
                .roles("USER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
