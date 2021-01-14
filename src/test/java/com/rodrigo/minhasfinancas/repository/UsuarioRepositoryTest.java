package com.rodrigo.minhasfinancas.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rodrigo.minhasfinancas.model.Usuario;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
	
		//Cenário
		Usuario usuario = Usuario.builder().nome("Rodrigo").email("rodrigo@email.com").build();
		usuarioRepository.save(usuario);
		
		
		//Ação
		boolean resultado = usuarioRepository.existsByEmail("rodrigo@email.com");
		
		
		//Verificação
		Assertions.assertThat(resultado).isTrue();
	}
}
