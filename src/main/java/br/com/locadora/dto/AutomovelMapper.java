package br.com.locadora.dto;

import br.com.locadora.model.Automovel;

public class AutomovelMapper {
	public static AutomovelDto consultaAutomovel(Automovel automoveis) {
		return new AutomovelDto(automoveis);
	}

	public static Automovel fromDto(AutomovelDto dto) {

		return new Automovel(null, dto.getPlaca(), dto.getCor(), dto.getModelo(), dto.getMarca(), dto.getNumPortas(),
				dto.getQuilometragem(), dto.getRenavan(), dto.getChassi(), dto.getValorDiaria(), dto.getDisponivel());
	}

}
