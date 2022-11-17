package br.com.locadora.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.locadora.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioTest {
	@MockBean
	UsuarioRepository usuarioRepository;
	
	@Test
	void Usuario() {
		Usuario user = new Usuario(null,"00022255541", "Marciel Bezerra", "marciel@email.com", "88884444555", 0, "1234", new Endereco("84555777", "5466", "casa"));
		Usuario userSalvo = usuarioRepository.save(user);
		
		assertEquals(user.getNome(), userSalvo.getNome());
		
	}
		
	}
//null, dto.getCpf(), dto.getNome(), dto.getEmail(), dto.getTelefone(), dto.getPermicao(), dto.getSenha(), dto.getEndereco()
