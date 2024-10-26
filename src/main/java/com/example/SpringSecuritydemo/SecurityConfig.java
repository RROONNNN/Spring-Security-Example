package com.example.SpringSecuritydemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
var configuer=new RobotLoginConfigurer();
	return	 http
				.authorizeHttpRequests(
						authorizeConfig->{
							authorizeConfig.requestMatchers("/").permitAll();
							authorizeConfig.anyRequest().authenticated();
						}
						)
			.formLogin(Customizer.withDefaults())
			.with(configuer, robotLoginConfigurer -> {
				robotLoginConfigurer.password("beep-boop");
				robotLoginConfigurer.password("boop-beep");
			})
		//	.apply(configuer.password("password")).and()
			.httpBasic(Customizer.withDefaults())//login thông quan phương thức  "http basic authentication" "curl -u "user:password" http://localhost:8080/private"
	.oauth2Login(Customizer.withDefaults())//login thông quan phương thức form authentication

			.build();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(
				User.builder()
				.username("user")
				.password("{noop}password")
				.authorities("ROLE_user")
				.build()
				);
	}

}
