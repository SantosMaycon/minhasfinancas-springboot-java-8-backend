package com.maycon.minhasfinancas.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.maycon.minhasfinancas.exceptions.RegraNegocioException;
import com.maycon.minhasfinancas.model.entities.Usuario;
import com.maycon.minhasfinancas.repositories.UsuarioRepository;

@SpringBootTest @ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	UsuarioService service;
	
	@Test
	public void deveValidarEmail() {
		Assertions.assertDoesNotThrow(() -> {
			//cenário
			repository.deleteAll();
			
			//ação
			service.validarEmail("mayccon@mail.com");
		});
	}
	
	@Test
	public void deveLancarErrorAoValidarEmailQuandoExistirEmailCadastrado() {
		Assertions.assertThrows(RegraNegocioException.class, () -> {
			//cenário
			repository.save(new Usuario(null, "nome", "email@mail.com", "senha"));
			
			//ação
			service.validarEmail("email@mail.com");
		});
	}
}
