package br.com.locadora.dto;

import br.com.locadora.model.Locacao;

public class LocacaoMapper {
	public static LocacaoDto consultaLocacao(Locacao locacao) {
		LocacaoDto locacaoDtoSenha = new LocacaoDto(locacao);
		locacaoDtoSenha.getUsuario().setSenha("**##***##*");
		
		return locacaoDtoSenha;
	}

	public static Locacao fromDto(LocacaoDto dto) {

		return new Locacao(null,dto.getDataLocacao(), dto.getDataDevolucao(), dto.getAutomovel(), dto.getUsuario(),dto.getQuilometragem(), dto.getValorCalcao(), dto.getValorLocacao());
	}
	
	
	
}
