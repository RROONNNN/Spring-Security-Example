package com.example.SpringSecuritydemo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpStatus;

import java.awt.*;
import java.io.IOException;
//README
//curl localhost:8080/private -H "robot-password:beep-beep" -v
//        and curl localhost:8080/private -H "robot-password:beep-beep" -v will be successful RonRobot
//       AuthenticationManager: The ProviderManager implements the AuthenticationManager interface, which defines a single method authenticate(Authentication authentication).
//AuthenticationProviders: The ProviderManager holds a list of AuthenticationProvider instances. Each AuthenticationProvider is responsible for a specific type of authentication.
//Delegation: When an authentication request is made, the ProviderManager iterates through its list of AuthenticationProvider instances and delegates the authentication request to each one until one of them successfully authenticates the request or all of them fail.
// tức l 1 request sẽ được gửi đến các AuthenticationProvider cho đến khi 1 trong số chúng xác thực thành công hoặc tất cả chúng đều thất bại.
//trong mỗi filter thường có 1 AuthenticationManager để xác thực request

public class RobotFilter extends OncePerRequestFilter {
    final private AuthenticationManager authManager;

    public RobotFilter(AuthenticationManager authManager) {
        this.authManager = authManager;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
String password = request.getHeader("robot-password");
if (password==null) {
    filterChain.doFilter(request, response);
    return ;
}
var authentication= RobotAuthentication.unAuthenticated(request.getHeader("robot-password"));
        try {
            var fullyAuthenticated = authManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(fullyAuthenticated);
            filterChain.doFilter(request, response);
            return ;
        } catch (Exception e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "text/plain");
            response.getWriter().write("You are not a robot");
            return;
        }
    }

}
