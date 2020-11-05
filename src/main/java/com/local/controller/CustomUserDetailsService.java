package com.local.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.local.entidades.Usuario;
import com.local.repositorios.IUsuario;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	IUsuario repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Usuario usuario = repo.findByUsername(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		UserDetails detallesUsuario = User.withUsername(usuario.getUsername())
			.password(usuario.getPass())
			.authorities("USER")
			.build();
		return detallesUsuario;
	}
	
}
