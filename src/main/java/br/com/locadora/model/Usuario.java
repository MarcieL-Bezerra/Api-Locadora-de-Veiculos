package br.com.locadora.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.locadora.dto.Permicao;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	// @CPF(message = "Favor verifique o campo CPF")
	@Size(min = 11, max = 11)
	private String cpf;
	@NotBlank(message = "Favor verifique o campo Nome")
	private String nome;
	@Email(message = "Favor verifique o campo Email")
	private String email;
	@NotBlank(message = "Favor verifique o campo Telefone")
	private String telefone;
	private Integer permicao = Permicao.CLIENTE;
	@NotBlank(message = "Favor verifique o campo Senha")
	private String senha;
	@Embedded
	private Endereco endereco;

	public Usuario() {

	}

	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(Long id, String cpf, String nome, String email, String telefone, Integer permicao, String senha,
			Endereco endereco) {
		super();
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
