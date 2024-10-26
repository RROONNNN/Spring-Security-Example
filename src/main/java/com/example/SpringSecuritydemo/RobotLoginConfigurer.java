package com.example.SpringSecuritydemo;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

public class RobotLoginConfigurer  extends AbstractHttpConfigurer<RobotLoginConfigurer, HttpSecurity> {
    final private List<String> passwords=new ArrayList<>();
    @Override
    public void init(HttpSecurity http) throws Exception {
        super.init(http);
        http.authenticationProvider(new RobotAuthenticationProvider(passwords));
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {

    var authManager = http.getSharedObject(AuthenticationManager.class);
    //AuthenticationManager in HttpSecurity is final , must get it from http.getSharedObject(AuthenticationManager.class)
        http.addFilterBefore(new RobotFilter(authManager), UsernamePasswordAuthenticationFilter.class);
    }
    public RobotLoginConfigurer password(String password) {
        this.passwords.add(password);
        return this;
    }
}
