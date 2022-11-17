package br.com.locadora.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_automovel")
public class Automovel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	@NotBlank(message = "Favor verifique o campo Placa")
	@Size(min = 7, max = 7)
	private String placa;
	@NotBlank(message = "Favor verifique o campo Cor")
	private String cor;
	@NotBlank(message = "Favor verifique o campo Modelo")
	private String modelo;
	@NotBlank(message = "Favor verifique o campo Marca")
	private String marca;
	@NotNull
	@Min(2)
	private Integer numPortas;
	@NotNull
	@Positive
	private Long quilometragem;
	@NotBlank(message = "Favor verifique o campo Renavan")
	private String renavan;
	@NotBlank(message = "Favor verifique o campo Chassi")
	private String chassi;
	@NotNull
	@Positive
	private BigDecimal valorDiaria;
	private Boolean disponivel = true;

	public Automovel() {

	}

	public Automovel(Long id, String placa, String cor, String modelo, String marca, Integer numPortas,
			Long quilometragem, String renavan, String chassi, BigDecimal valorDiaria, Boolean disponivel) {
		this.id = id;
		this.placa = placa;
		this.cor = cor;
		this.modelo = modelo;
		this.marca = marca;
		this.numPortas = numPortas;
		this.quilometragem = quilometragem;
		this.renavan = renavan;
		this.chassi = chassi;
		this.valorDiaria = valorDiaria;
		this.disponivel = disponivel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getNumPortas() {
		return numPortas;
	}

	public void setNumPortas(Integer numPortas) {
		this.numPortas = numPortas;
	}

	public Long getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getRenavan() {
		return renavan;
	}

	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(BigDecimal valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

}
