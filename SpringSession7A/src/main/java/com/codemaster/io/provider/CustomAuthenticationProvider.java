package com.codemaster.io.provider;

import com.codemaster.io.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("CustomAuthenticationProvider");

        if (authentication instanceof UsernamePasswordAuthenticationToken) {

            String username = authentication.getName();
            String credentials = authentication.getCredentials().toString();

            System.out.println("username = " + username);
            System.out.println("credentials = " + credentials);

            UserDetails userDetails = userService.loadUserByUsername(username);
            if (userDetails == null) {
                throw new UsernameNotFoundException("User not found");
            }

            // check if pass matches
            if (passwordEncoder.matches(credentials, userDetails.getPassword())) {
                // Auth success, return an authenticated token
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
            } else {
                // auth failed
                throw new RuntimeException("Invalid password");
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
