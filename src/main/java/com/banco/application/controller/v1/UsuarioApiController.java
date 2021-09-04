package com.banco.application.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.banco.application.config.security.JwtService;
import com.banco.application.dto.CredenciaisDto;
import com.banco.application.dto.TokenDto;
import com.banco.application.models.UsuarioApi;
import com.banco.application.service.UsuarioServiceApi;

@RestController
@RequestMapping("/api/usuariosApi")
public class UsuarioApiController {

	@Autowired
	private UsuarioServiceApi usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	public ResponseEntity<UsuarioApi> salvar(@RequestBody UsuarioApi usuario){
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return new ResponseEntity<UsuarioApi>(usuarioService.salvar(usuario), HttpStatus.CREATED);
	}
	
	@PostMapping("/auth")
	public TokenDto autenticar(@RequestBody CredenciaisDto credenciais) {
		try {
			UsuarioApi usuario = new UsuarioApi();
			usuario.setLogin(credenciais.getLogin());
			usuario.setSenha(credenciais.getSenha());
			
			UserDetails userDetails = usuarioService.autenticar(usuario);
			
			String token = jwtService.gerarToken(usuario);
			
			return new TokenDto(usuario.getLogin(), token);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
}
