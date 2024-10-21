package com.codemaster.io.config;

import com.codemaster.io.JwtManagementFilter;
import com.codemaster.io.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // Enable method-level security
public class SecurityConfig {

    @Autowired
    JwtManagementFilter jwtManagementFilter;

    @Bean
    public UserDetailsService userDetailsService() {

        User user = new User("user", "1234",
                List.of(new SimpleGrantedAuthority("ROLE_USER")));


        User admin = new User("admin", "1234",
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ADMIN:ALL_PERMISSION")));

        User adminViewer = new User("admin_viewer", "1234",
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ADMIN:READ_PERMISSION")));

        User moderator = new User("moderator", "1234",
                List.of(new SimpleGrantedAuthority("ROLE_MODERATOR"),
                        new SimpleGrantedAuthority("MODERATOR:ALL_PERMISSION")));

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user, admin, moderator, adminViewer);

        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .antMatchers("/").permitAll()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/dashboard").hasAnyRole("ADMIN", "MODERATOR")
//                .antMatchers("/user").hasAnyRole("ADMIN", "MODERATOR", "USER")
                .anyRequest().authenticated();

        http.csrf().disable();

        http.addFilterBefore(jwtManagementFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());

        http.httpBasic();
        http.formLogin();

        return http.build();
    }
}
