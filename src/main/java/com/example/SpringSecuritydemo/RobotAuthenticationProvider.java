package com.example.SpringSecuritydemo;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.List;

public class RobotAuthenticationProvider implements AuthenticationProvider {
    final private List<String> passwords;

    public RobotAuthenticationProvider(List<String> passwords) {
        this.passwords = passwords;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String password= (String) ( (RobotAuthentication) authentication).getPassword();
        if(passwords.contains(password)) {
            return RobotAuthentication.Authenticated();
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return RobotAuthentication.class.isAssignableFrom(authentication);
    }
}
