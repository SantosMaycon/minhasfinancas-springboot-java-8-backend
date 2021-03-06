package com.maycon.minhasfinancas.services;

import com.maycon.minhasfinancas.model.entities.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
}
