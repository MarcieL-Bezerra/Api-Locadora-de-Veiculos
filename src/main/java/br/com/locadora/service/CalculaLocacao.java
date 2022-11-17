package br.com.locadora.service;

import java.math.BigDecimal;

public class CalculaLocacao {
	
	public BigDecimal calculaValorLocacao(Integer qtdDias, BigDecimal valorDiaria) {
		
		BigDecimal diasCorridos = new BigDecimal(qtdDias);
		BigDecimal valorLocacao = valorDiaria.multiply(diasCorridos);
		
		return valorLocacao;
	}
}
