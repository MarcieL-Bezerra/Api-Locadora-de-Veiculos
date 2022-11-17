package br.com.locadora.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.locadora.model.Automovel;
import br.com.locadora.model.Locacao;
import br.com.locadora.model.Usuario;

public class LocacaoDto extends RepresentationModel<LocacaoDto>{
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataLocacao;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataDevolucao;
	private Automovel automovel;
	private Usuario usuario;
	private Long quilometragem;
	private BigDecimal valorCalcao;
	private BigDecimal valorLocacao;
	
	public LocacaoDto() {
		
	}

	public LocacaoDto(Locacao locacao) {
		this.id = locacao.getId();
		this.dataLocacao = locacao.getDataLocacao();
		this.dataDevolucao = locacao.getDataDevolucao();
		this.automovel = locacao.getAutomovel();
		this.usuario = locacao.getUsuario();
		this.quilometragem = locacao.getQuilometragem();
		this.valorCalcao = locacao.getValorCalcao();
		this.valorLocacao = locacao.getValorLocacao();
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
