package br.com.locadora.service;

import java.util.Optional;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.locadora.exception.EntityNotFoundException;
import br.com.locadora.model.Usuario;
import br.com.locadora.repository.UsuarioRepository;

@Service
public class UsuarioService {

	UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;

	}

	public Usuario salvarUsuario(Usuario usuario) {

		return usuarioRepository.save(usuario);
	}

	public Page<Usuario> listarTodosOsUsuarios(Pageable pageable) {

		return usuarioRepository.findAll(pageable);

	}

	public Usuario buscarUsuario(String id) {
		if (id.length() == 11) {
			return buscarUsuarioPorCpf(id);
		}
		return buscarUsuarioPorId(Long.parseLong(id));
	}

	public Usuario buscarUsuarioPorId(Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);

		return optional.orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
	}

	public Usuario buscarUsuarioPorCpf(String cpf) {
		Optional<Usuario> optional = usuarioRepository.findByCpf(cpf);

		return optional.orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

	}

	public Usuario atualizarUsuario(Usuario usuario, Long id) {
			Usuario UsuarioOriginal = this.buscarUsuarioPorId(id);
			usuario.setId(UsuarioOriginal.getId());
			
			return usuarioRepository.save(usuario);
	}
			
		

	public ResponseEntity<String> excluirUsuario(Long id) {
		try {
			Usuario UsuarioOriginal = this.buscarUsuarioPorId(id);
			usuarioRepository.delete(UsuarioOriginal);

			return new ResponseEntity<>("Usuario excluído", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Usuario não pode ser excluído, Verifique sua existência ou relações!",
					HttpStatus.BAD_REQUEST);
		}

	}

}
