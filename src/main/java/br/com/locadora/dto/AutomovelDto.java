package br.com.locadora.dto;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import br.com.locadora.model.Automovel;

public class AutomovelDto extends RepresentationModel<AutomovelDto> {
	private Long id;
	private String placa;
	private String cor;
	private String modelo;
	private String marca;
	private int numPortas;
	private Long quilometragem;
	private String renavan;
	private String chassi;
	private BigDecimal valorDiaria;
	private Boolean disponivel = true;

	public AutomovelDto() {

	}

	public AutomovelDto(Automovel automovel) {
		this.id = automovel.getId();
		this.placa = automovel.getPlaca();
		this.cor = automovel.getCor();
		this.modelo = automovel.getModelo();
		this.marca = automovel.getMarca();
		this.numPortas = automovel.getNumPortas();
		this.quilometragem = automovel.getQuilometragem();
		this.renavan = automovel.getRenavan();
		this.chassi = automovel.getChassi();
		this.valorDiaria = automovel.getValorDiaria();
		this.disponivel = automovel.getDisponivel();
	}

	public Long getId() {
		return id;
	}

	public String getPlaca() {
		return placa;
	}

	public String getCor() {
		return cor;
	}

	public String getModelo() {
		return modelo;
	}

	public String getMarca() {
		return marca;
	}

	public int getNumPortas() {
		return numPortas;
	}

	public Long getQuilometragem() {
		return quilometragem;
	}

	public String getRenavan() {
		return renavan;
	}

	public String getChassi() {
		return chassi;
	}

	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

}
