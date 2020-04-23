 package com.maycon.minhasfinancas.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.maycon.minhasfinancas.model.entities.Usuario;

@SpringBootTest @ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	@Autowired
	UsuarioRepository repository;
	
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenário
		Usuario usuario = new Usuario(null, "usuario", "usuario@mail.com", "senha");
		repository.save(usuario);
		
		//ação/ execução
		boolean resultado = repository.existsByEmail("usuario@mail.com");
		
		//verificação
		 Assertions.assertThat(resultado).isTrue();
		
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {
		//cenário
		repository.deleteAll();
		
		//ação/ execução
		boolean resultado = repository.existsByEmail("maycon@mail.com");
		
		//verificação
		Assertions.assertThat(resultado).isFalse();
	}
	
}
