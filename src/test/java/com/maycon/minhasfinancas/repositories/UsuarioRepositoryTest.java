 package com.maycon.minhasfinancas.repositories;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.maycon.minhasfinancas.model.entities.Usuario;

@DataJpaTest @AutoConfigureTestDatabase(replace = Replace.NONE) @ExtendWith(SpringExtension.class) 
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	
	public Usuario criarUsuario() {
		return new Usuario(null, "nome", "email@email.com", "senha");
	}
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		//ação/ execução
		boolean resultado = repository.existsByEmail("email@email.com");
		
		//verificação
		 Assertions.assertThat(resultado).isTrue();
		
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {
		//cenário
		
		
		//ação/ execução
		boolean resultado = repository.existsByEmail("email@email.com");
		
		//verificação
		Assertions.assertThat(resultado).isFalse();
	}
	
	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		//cenário
		Usuario usuario = criarUsuario();
		
		//ação
		Usuario usuarioRetornadoDoBanco = repository.save(usuario);
		
		//verificação
		Assertions.assertThat(usuarioRetornadoDoBanco.getId()).isNotNull();
	}
	
	@Test
	public void deveRetornarUmUsuarioPorEmail() {
		//cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		//ação
		Optional<Usuario> usuarioRetornadoDoBanco = repository.findByEmail("email@email.com");
		
		//verificação
		Assertions.assertThat(usuarioRetornadoDoBanco).isNotEmpty();	
	}
	
	@Test
	public void deveRetornarVazioQiandoBuscarUmUsuarioQueNãoExisteNaBaseDeDados() {
		//cenário
		
		//ação
		Optional<Usuario> usuarioRetornadoDoBanco = repository.findByEmail("email@email.com");
		
		//verificação
		Assertions.assertThat(usuarioRetornadoDoBanco).isEmpty();	
	}
}
