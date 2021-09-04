package com.banco.application.config.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.banco.application.models.UsuarioApi;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${security.jwt.assinatura}")
	private String chaveAssinatura;
	
	public String gerarToken(UsuarioApi usuarioApi) {
		long expString = Long.valueOf(expiracao);
		
		LocalDateTime dataHoraExpiracao = LocalDateTime.now()
				.plusMinutes(expString);
		
		Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());
		
		return Jwts.builder()
				.setSubject(usuarioApi.getLogin())
				.setExpiration(data)
				.signWith(SignatureAlgorithm.HS512, chaveAssinatura)
				.compact();
	}
	
	public Claims obterDados(String token) throws ExpiredJwtException{
		return Jwts.parser()
				.setSigningKey(chaveAssinatura)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public boolean tokenValido(String token) {
		try {
			Claims claims = obterDados(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime localDt = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			
			return !LocalDateTime.now().isAfter(localDt);
		}catch(Exception e) {
			return false;
		}
	}
	
	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return (String) this.obterDados(token).getSubject();
	}
}
