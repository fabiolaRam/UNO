package com.backend.graficaInforma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
 class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;



	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder

		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select USERNAME, PASSWORD, AVISO from USERS where USERNAME=? ")
		.authoritiesByUsernameQuery("select USERNAME, AUTHORITY from AUTHORITIES where USERNAME=? ")
		.passwordEncoder(passwordEncoder())
		;
	}



	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}



	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Disable cookies
			.and().cors() //Put the Cross-Origin Resource Sharing at default values
			.and().csrf()
			.disable() // Disable the Cross-site request forgery filter - could cause that any 
			.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests() 
			.antMatchers(HttpMethod.POST, "/backEGInforma/login").permitAll() // Login doesn't need authorization
		    .antMatchers("/backEGInforma/olvidoPassword/*","/backEGInforma/enviaClaveAcceso/*").permitAll()
			.anyRequest().authenticated(); // All other pages are securized

	}
}
