package com.maycon.minhasfinancas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maycon.minhasfinancas.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	boolean existsByEmail(String email);
	
	Optional<Usuario> findByEmail(String email);
}
