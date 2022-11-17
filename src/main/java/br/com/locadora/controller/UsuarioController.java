package br.com.locadora.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.locadora.dto.UsuarioDto;
import br.com.locadora.dto.UsuarioMapper;
import br.com.locadora.model.Usuario;
import br.com.locadora.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<Page<UsuarioDto>> buscarTodaOsUsuarios(@PageableDefault Pageable pageable) {
		Page<UsuarioDto> userRecebidos = usuarioService.listarTodosOsUsuarios(pageable)
				.map(UsuarioMapper::consultaUsuario);
		for (UsuarioDto userRecebido : userRecebidos) {
			String id = userRecebido.getId().toString();
			userRecebido.add(linkTo(methodOn(UsuarioController.class).buscarUsuario(pageable, id)).withRel("Buscar (GET), Alterar(PUT) ou Excluir(DEL) Usuário por ID"));
			String cpf = userRecebido.getCpf();
			userRecebido.add(linkTo(methodOn(UsuarioController.class).buscarUsuario(pageable, cpf)).withRel("Buscar (GET) Usuário por CPF"));

		}

		return ResponseEntity.ok(userRecebidos);
	}

	@PostMapping
	public ResponseEntity<UsuarioDto> salvarUsuario(@RequestBody @Valid UsuarioDto dto, UriComponentsBuilder uriComponentsBuilder) {
		Usuario usuario = usuarioService.salvarUsuario(UsuarioMapper.fromDto(dto));
		UsuarioDto usuarioSalvo = UsuarioMapper.consultaUsuario(usuario);
		
		URI uri = uriComponentsBuilder.path("/usuarios{id}").buildAndExpand(usuarioSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(usuarioSalvo);

	}

	@GetMapping("{idCpf}")
	public ResponseEntity<UsuarioDto> buscarUsuario(@PageableDefault Pageable pageable,
			@PathVariable(value = "idCpf") String cpf) {

		Usuario usuario = usuarioService.buscarUsuario(cpf);
		UsuarioDto usuarioDto = UsuarioMapper.consultaUsuario(usuario);
		usuarioDto.add(linkTo(methodOn(UsuarioController.class).buscarTodaOsUsuarios(pageable)).withRel("Lista de Usuários"));
		return ResponseEntity.ok(usuarioDto);

	}

	@PutMapping("{id}")
	public ResponseEntity<UsuarioDto> alterarUsuario(@RequestBody @Valid UsuarioDto dto, @PathVariable Long id) {
		try {
			Usuario usuario = usuarioService.atualizarUsuario(UsuarioMapper.fromDto(dto), id);

			return ResponseEntity.ok(UsuarioMapper.consultaUsuario(usuario));
		} catch (RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> excluirUsuario(@PathVariable Long id) {
			return usuarioService.excluirUsuario(id);
		}
}
