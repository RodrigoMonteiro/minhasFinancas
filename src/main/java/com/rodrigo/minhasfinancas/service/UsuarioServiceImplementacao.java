package com.rodrigo.minhasfinancas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.minhasfinancas.exceptions.RegraNegocioException;
import com.rodrigo.minhasfinancas.model.Usuario;
import com.rodrigo.minhasfinancas.repository.UsuarioRepository;

@Service
public class UsuarioServiceImplementacao implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioServiceImplementacao(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
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
		boolean existe = usuarioRepository.existsByEmail(email);
		if (existe) {
			throw new RegraNegocioException("Já existe caastro com este e-mail.");
		}
	}

}
