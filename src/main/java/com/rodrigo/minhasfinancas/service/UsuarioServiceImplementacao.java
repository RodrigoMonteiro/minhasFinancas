package com.rodrigo.minhasfinancas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rodrigo.minhasfinancas.exceptions.ErroDeAutenticacaoException;
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
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

		if (!usuario.isPresent()) {
			throw new ErroDeAutenticacaoException("Usuário não encontrado para o e-mail informado!");
		}
		
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroDeAutenticacaoException("Senha inválida!");
		}

		return usuario.get();
	}

// O transactional vai criar uma transação  vai executar e vai commitar
	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return usuarioRepository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = usuarioRepository.existsByEmail(email);
		if (existe) {
			throw new RegraNegocioException("Já existe caastro com este e-mail.");
		}
	}

}
