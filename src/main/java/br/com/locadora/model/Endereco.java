package br.com.locadora.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {
	@NotBlank(message = "Favor verifique o campo Cep")
	private String cep;
	@NotBlank(message = "Favor verifique o campo Numero")
	private String numero;
	private String complemento;

	
	
	public Endereco() {
		}

	public Endereco(@NotBlank(message = "Favor verifique o campo Cep") String cep,
			@NotBlank(message = "Favor verifique o campo Numero") String numero, String complemento) {
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
