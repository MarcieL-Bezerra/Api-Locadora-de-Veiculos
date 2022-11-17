package br.com.locadora.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_locacao")
public class Locacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataLocacao;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataDevolucao;
	@NotNull
	@ManyToOne
	private Automovel automovel;
	@NotNull
	@ManyToOne
	private Usuario usuario;
	@NotNull
	@Positive
	private Long quilometragem;
	@NotNull
	@Positive
	private BigDecimal valorCalcao;

	private BigDecimal valorLocacao;

	public Locacao() {

	}

	public Locacao(Long id) {
		this.id = id;
	}

	public Locacao(Long id, LocalDateTime dataLocacao, LocalDateTime dataDevolucvcao, Automovel automovel,
			Usuario usuario, Long quilometragem, BigDecimal valorCalcao, BigDecimal valorLocacao) {
		this.id = id;
		this.dataLocacao = dataLocacao;
		this.dataDevolucao = dataDevolucvcao;
		this.automovel = automovel;
		this.usuario = usuario;
		this.quilometragem = quilometragem;
		this.valorCalcao = valorCalcao;
		this.valorLocacao = valorLocacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(LocalDateTime dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public LocalDateTime getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDateTime dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}

	public BigDecimal getValorCalcao() {
		return valorCalcao;
	}

	public void setValorCalcao(BigDecimal valorCalcao) {
		this.valorCalcao = valorCalcao;
	}

	public BigDecimal getValorLocacao() {
		return valorLocacao;
	}

	public void setValorLocacao(BigDecimal valorLocacao) {
		this.valorLocacao = valorLocacao;
	}

}
