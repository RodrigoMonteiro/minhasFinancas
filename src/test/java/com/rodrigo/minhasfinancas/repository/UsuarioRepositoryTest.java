package com.rodrigo.minhasfinancas.repository;

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

import com.rodrigo.minhasfinancas.model.Usuario;

// O autoConfigureDataBase nao vai sobrescrever as configuraçao do banco (application.properties)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TestEntityManager entityManager;

	public static Usuario criarUsuario() {
		return Usuario.builder().nome("usuario").email("usuario@email.com").build();
	}

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {

		// Cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		// Ação
		boolean resultado = usuarioRepository.existsByEmail("usuario@email.com");

		// Verificação
		Assertions.assertThat(resultado).isTrue();
	}

	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {

		// Cenário

		// Ação
		boolean resultado = usuarioRepository.existsByEmail("rodrigo@email.com");

		// Verificação
		Assertions.assertThat(resultado).isFalse();
	}

	@Test
	public void devePersistirUmUsuarioNaBaseeDados() {
		// Cenário
		Usuario usuario = criarUsuario();
		// Ação
		usuarioRepository.save(usuario);

		// Verificação
		Assertions.assertThat(usuario.getId()).isNotNull();
	}

	@Test
	public void deveBuscarUsuarioPorEmail() {

		// Cenário

		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);

		// Ação

		// Verificação
		Optional<Usuario> resultado = usuarioRepository.findByEmail("usuario@email.com");
		Assertions.assertThat(resultado.isPresent()).isTrue();
	}

	@Test
	public void deveRetornarVazioAoBuscarUsuarioPorEmail() {

		// Cenário

		// Ação

		// Verificação
		Optional<Usuario> resultado = usuarioRepository.findByEmail("usuario@email.com");
		Assertions.assertThat(resultado.isPresent()).isFalse();
	}

	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {

		// Cenario
		Usuario usuario = criarUsuario();

		// Ação
		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		// Verficação
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}

}
