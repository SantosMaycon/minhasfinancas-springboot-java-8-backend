package com.maycon.minhasfinancas.services.impl;

import com.maycon.minhasfinancas.exceptions.RegraNegocioException;
import com.maycon.minhasfinancas.model.entities.Usuario;
import com.maycon.minhasfinancas.repositories.UsuarioRepository;
import com.maycon.minhasfinancas.services.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{

	private UsuarioRepository repository;
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email!");
		}
	}

}
