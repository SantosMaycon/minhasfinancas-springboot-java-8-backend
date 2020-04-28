package com.maycon.minhasfinancas.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.maycon.minhasfinancas.exceptions.ErrorAutenticacao;
import com.maycon.minhasfinancas.exceptions.RegraNegocioException;
import com.maycon.minhasfinancas.model.entities.Usuario;
import com.maycon.minhasfinancas.repositories.UsuarioRepository;
import com.maycon.minhasfinancas.services.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@SpyBean
	UsuarioServiceImpl service;
	
	@MockBean
	UsuarioRepository repository;
	
	@BeforeEach
	public void setUp() {
		service = new UsuarioServiceImpl(repository);
	}
	
	@Test
	public void deveAutenticarUmUsuarioComSucesso() {
		Assertions.assertDoesNotThrow(() -> {
			//cenário
			Usuario usuario = new Usuario(11L, "nome", "email@email.com", "senha");
			Mockito.when(repository.findByEmail("email@email.com")).thenReturn(Optional.of(usuario));
			
			//ação
			service.autenticar("email@email.com", "senha");
		});
	}
	
	@Test
	public void deveLancarErrorAoAutenticarQuandoOEmailNaoForValido() {
		
		//cenário
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		
		//ação
		Throwable exception = org.assertj.core.api.Assertions.catchThrowable(() -> service.autenticar("email@email.com", "123"));
		org.assertj.core.api.Assertions.assertThat(exception).isInstanceOf(ErrorAutenticacao.class).hasMessage("Usuário não encontrado para o email informado.");
	}
	
	@Test
	public void deveLancarErroAoAutenticarASenhaComoInvalida() {
		
			//cenário
			Usuario usuario = new Usuario(11L, "nome", "email@email.com", "senha");
			Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
			
			//ação
			Throwable exception = org.assertj.core.api.Assertions.catchThrowable(() -> service.autenticar("email@email.com", "123"));
			org.assertj.core.api.Assertions.assertThat(exception).isInstanceOf(ErrorAutenticacao.class).hasMessage("Senha inválida.");
	}
	
	
	@Test
	public void deveValidarEmail() {
		Assertions.assertDoesNotThrow(() -> {
			//cenário
			Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
			
			//ação
			service.validarEmail("mayccon@mail.com");
		});
	}
	
	@Test
	public void deveLancarErrorAoValidarEmailQuandoExistirEmailCadastrado() {
		Assertions.assertThrows(RegraNegocioException.class, () -> {
			//cenário
			Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
			
			//ação
			service.validarEmail("email@mail.com");
		});
	}
	
//	@Test
//	public void deveSalavarUmUsuarioComSucesso() {
//		//cenário
//		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
//		Usuario usuario = new Usuario(1L, "nome", "email@mail", "senha");
//		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario); 
//		
//		//ação
//		Usuario usuarioSalvo = service.salvarUsuario(new Usuario());
//		
//		//verificação
//		org.assertj.core.api.Assertions.assertThat(usuarioSalvo).isNotNull();
//		org.assertj.core.api.Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1L);
//		org.assertj.core.api.Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
//		org.assertj.core.api.Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("email@mail.com");
//		org.assertj.core.api.Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("senha");
//	}
//	
//	@Test
//	public void naoDeveSalvarUmUsuarioComEmailJaCadastrado() {
//		//cenário
//		Usuario usuario = new Usuario(null, "nome", "email@mail.com", "senha");
//		Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail("email@mail.com");
//		
//		//ação
//		service.salvarUsuario(usuario);
//	}
}
