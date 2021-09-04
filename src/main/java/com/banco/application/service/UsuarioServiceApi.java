package com.banco.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banco.application.Repository.UsuarioApiRepository;
import com.banco.application.exceptions.SenhaInvalidaException;
import com.banco.application.models.UsuarioApi;

@Service
public class UsuarioServiceApi implements UserDetailsService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioApiRepository usuarioRepository;
	
	public UsuarioApi salvar(UsuarioApi usuarioApi) {
		return usuarioRepository.save(usuarioApi);
	}
	
	public UserDetails autenticar(UsuarioApi usuarioApi) {
		//loadUserByUsername é herdado do UserDetailsService.
		UserDetails userDetails = loadUserByUsername(usuarioApi.getLogin());
		//Verifica se a senha resgatada do banco a partir do login é igual a senha passada como parâmetro
		Boolean senhasConferem = encoder.matches(usuarioApi.getSenha(), userDetails.getPassword());
		if(senhasConferem) {
			return userDetails;
		}
		throw new SenhaInvalidaException();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioApi usuarioApi = usuarioRepository.findByLogin(username).get();
		
		String[] roles = usuarioApi.isAdmin() ?
				new String[] {"GESTOR", "USUARIO"} : 
					new String[] {"USUARIO"};
		
		return User.builder()
				.username(usuarioApi.getLogin())
				.password(usuarioApi.getSenha())
				.roles(roles)
				.build();
	}
}
