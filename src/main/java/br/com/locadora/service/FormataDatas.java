package br.com.locadora.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class FormataDatas {

	public LocalDateTime RetornaDataAtual() {
		Date DataAtual = Calendar.getInstance().getTime();
		SimpleDateFormat dataF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataFormatada = dataF.format(DataAtual);
		LocalDateTime dataAtualFormatada = LocalDateTime.parse(dataFormatada,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		return dataAtualFormatada;
	}

	public Integer CalculaQtdDias(LocalDateTime DataInicia, LocalDateTime dataFinal) {
		Integer horasLocados = Math.toIntExact(ChronoUnit.HOURS.between(DataInicia, dataFinal));

		if (horasLocados <= 0)
			return 1;
		else if ((horasLocados % 24) == 0)
			return horasLocados / 24;
		else
			return (horasLocados / 24) + 1;

	}

	public LocalDateTime ComparaDatas(LocalDateTime DataInicia, LocalDateTime dataFinal) {
		if (dataFinal.isBefore(DataInicia)) {
			return DataInicia;
		}
		return dataFinal;
	}

}
