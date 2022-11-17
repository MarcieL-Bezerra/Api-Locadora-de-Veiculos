package br.com.locadora.dto;

import javax.persistence.Embedded;

import org.springframework.hateoas.RepresentationModel;

import br.com.locadora.model.Endereco;
import br.com.locadora.model.Usuario;

public class UsuarioDto extends RepresentationModel<UsuarioDto>{
	private Long id;
	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	private Integer permicao;
	private String senha;
	@Embedded
	private Endereco endereco;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.cpf = usuario.getCpf();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.telefone = usuario.getTelefone();
		this.permicao = usuario.getPermicao();
		this.endereco = usuario.getEndereco();
		this.senha = "**##***##*";
	}

	public UsuarioDto(Long id, String cpf, String nome, String email, String telefone, Integer permicao, String senha,
			Endereco endereco) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.permicao = permicao;
		this.senha = senha;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getPermicao() {
		return permicao;
	}

	public void setPermicao(Integer permicao) {
		this.permicao = permicao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
