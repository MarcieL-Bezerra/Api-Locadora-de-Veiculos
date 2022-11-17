package br.com.locadora.dto;

import br.com.locadora.model.Usuario;

public class UsuarioMapper {
	public static UsuarioDto consultaUsuario(Usuario usuario) {
		return new UsuarioDto(usuario);
	}

	public static Usuario fromDto(UsuarioDto dto) {

		return new Usuario(null, dto.getCpf(), dto.getNome(), dto.getEmail(), dto.getTelefone(), dto.getPermicao(), dto.getSenha(), dto.getEndereco());
	}
	
	
}
