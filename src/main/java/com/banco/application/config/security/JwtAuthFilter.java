package com.banco.application.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.banco.application.service.UsuarioServiceApi;

public class JwtAuthFilter extends OncePerRequestFilter{

	private JwtService jwtService;
	private UsuarioServiceApi usuarioServiceApi;
	
	public JwtAuthFilter(JwtService jwtService, UsuarioServiceApi usuarioServiceApi) {
		super();
		this.jwtService = jwtService;
		this.usuarioServiceApi = usuarioServiceApi;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization");
		
		if(authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.split(" ")[1];
			Boolean tokenValido = jwtService.tokenValido(token);
			
			if(tokenValido) {
				String loginUsuario = jwtService.obterLoginUsuario(token);
				UserDetails userDetails = usuarioServiceApi.loadUserByUsername(loginUsuario);
				UsernamePasswordAuthenticationToken userAuth =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userAuth);
			}
		}
		
		filterChain.doFilter(request, response);
	}
}
