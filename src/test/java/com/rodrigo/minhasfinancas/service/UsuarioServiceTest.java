package com.rodrigo.minhasfinancas.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rodrigo.minhasfinancas.exceptions.RegraNegocioException;
import com.rodrigo.minhasfinancas.model.Usuario;
import com.rodrigo.minhasfinancas.repository.UsuarioRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	
	

	public void deveValidarEmail() {

		// Cenário
		usuarioRepository.deleteAll();

		// Ação
		usuarioService.validarEmail("rodrigo@email.com");

		

	}

	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		Assertions.assertThrows(RegraNegocioException.class, () -> {
			// cenario
			Usuario usuario = Usuario.builder().nome("rodrigo").email("rodrigo@email.com").build();
			usuarioRepository.save(usuario);

			// acao
			usuarioService.validarEmail("rodrigo@email.com");
		});
	}
}
