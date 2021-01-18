package com.rodrigo.minhasfinancas.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rodrigo.minhasfinancas.exceptions.RegraNegocioException;
import com.rodrigo.minhasfinancas.model.Usuario;
import com.rodrigo.minhasfinancas.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	@SpyBean
	UsuarioService usuarioService;

	@MockBean
	UsuarioRepository usuarioRepository;

	@Test
	public void deveSalvarUmUsuario() {
		// O spy nada mais é que um mock ,apenas com uma pequena diferença, ele chamam
		// os metodos orifinais (service = spy, repository = mock)
		// Sem deixar o spring gerenciar...

		// usuarioService = Mockito.spy(UsuarioService.class);

		// Cenário
		Mockito.doNothing().when(usuarioService).validarEmail(Mockito.anyString());
		Usuario usuario = Usuario.builder().id(1l).nome("usuario").senha("email@email.com").build();
		Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

		// Ação
		usuarioService.salvarUsuario(usuario);

		// Verificação
// Assertions.assertThat(usuario.getId()).equals(1l);

	}

// ANTES DO SPYBEAN
//	@BeforeEach
//	public void SetUp() {
//		// O spy nada mais é que um mock ,apenas com uma pequena diferença, ele chamam
//				// os metodos originais (service = spy, repository = mock)
//				usuarioService = Mockito.spy(UsuarioService.class);
//
//		
//		// usuarioRepository = Mockito.mock(UsuarioRepository.class);
//		// usuarioService = new UsuarioServiceImplementacao(usuarioRepository); (Antes do Spy)
//	}

	@Test
	public void deveValidarEmail() {

		// Cenário
		Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(false);

		// Ação
		usuarioService.validarEmail("rodrigo@email.com");

	}

	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		Assertions.assertThrows(RegraNegocioException.class, () -> {
			// cenario
			Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

			// acao
			usuarioService.validarEmail("rodrigo@email.com");
		});
	}

	@Test
	public void deveAutenticarUmUsuarioComSucesso() {

		// Cenário
		String email = "usuario@email.com";
		String senha = "senha";
		Usuario usuario = Usuario.builder().senha(senha).email(email).id(1l).build();
		Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

		// Ação
		Usuario usuarioResultado = usuarioService.autenticar(email, senha);

		// Verificação
		Assertions.assertNotNull(usuarioResultado);
	}

	@Test
	public void deveLancarErroQuandoNaoEncontrarUsuarioComOEmailInformado() {

		// Cenário
		Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

		// Ação
		usuarioService.autenticar("usuario@email.com", "senha");

		// Verificação

	}

	@Test
	public void deveLancarErroQuandoSenhaNaoBater() {

		// Cenário
		String senha = "senha";
		Usuario usuario = Usuario.builder().senha(senha).email("usuario@email.com").build();
		Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

		// Ação
		// Throwable exception = Assertions.catchThrowable(() ->
		// usuarioService.autenticar("usuario@email.com","123"));
		// Assertions.assertThat(exception).isInstanceOf(ErroAutenticacaoException.class).hasMessage("Senha
		// inválida!");
		// Verificação
	}

	@Test
	public void naoDeveSalvarUmUsuarioComEmailJaCadastrado() {
		// Cenário
		String senha = "usuario@email.com";
		Usuario usuario = Usuario.builder().email(senha).build();
		Mockito.doThrow(RegraNegocioException.class).when(usuarioService).validarEmail(senha);

		// Ação
		usuarioService.salvarUsuario(usuario);

		// Verificação
		Mockito.verify(usuarioRepository, Mockito.never()).save(usuario);
	}

}
