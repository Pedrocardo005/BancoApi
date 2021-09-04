package com.banco.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.banco.application.config.security.JwtAuthFilter;
import com.banco.application.config.security.JwtService;
import com.banco.application.service.UsuarioServiceApi;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UsuarioServiceApi usuarioServiceApi;
	
	@Autowired
	JwtService jwtService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, usuarioServiceApi);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioServiceApi)
		.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/v1/**").hasRole("USUARIO")//EXIGINDO papel admin
		.and()//permite adicionar mais coisas a estrutura
		.httpBasic();
		
		/*
		http.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/v1/**").hasRole("USUARIO")
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
		*/
	}

}
