package com.example.SpringSecuritydemo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;

public class RobotAuthentication implements Authentication {
   final private boolean isAuthenticated ;//= true;
    final private List<GrantedAuthority> authorities ;//= AuthorityUtils.createAuthorityList("ROLE_ROBOT");
    final private String name="RonRobot"; ;
    final private String password ;


    public RobotAuthentication( List<GrantedAuthority> authorities, String password) {
        this.isAuthenticated = password==null;
        this.password = password;
        this.authorities = authorities;

    }
    public static RobotAuthentication unAuthenticated(String password) {

        return new RobotAuthentication(AuthorityUtils.createAuthorityList(), password);
    }
    public static RobotAuthentication Authenticated() {

        return new RobotAuthentication(AuthorityUtils.createAuthorityList("ROLE_ROBOT"), null);
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public Object getPrincipal() {
        return getName();
    }

    @Override
    public Object getDetails() {
        return null;
    }

public String getPassword() {
    return password;
}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }


    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (!isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
    }
}
