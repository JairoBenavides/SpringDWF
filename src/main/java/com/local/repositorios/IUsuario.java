package com.local.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.local.entidades.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer>{
	
	Usuario findByUsername(String username);
	
}
