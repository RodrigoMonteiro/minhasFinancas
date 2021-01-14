package com.rodrigo.minhasfinancas.service;

import com.rodrigo.minhasfinancas.model.Usuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);

	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
}
