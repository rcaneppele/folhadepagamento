package br.com.rcaneppele.folhadepagamento.calculofolhamensal;

import java.math.BigDecimal;

public class FolhadePagamento {

	private final BigDecimal somatorioSalarios;
	private final BigDecimal somatorioFGTS;
	private final BigDecimal somatorioINSS;

	public FolhadePagamento(BigDecimal somatorioSalarios, BigDecimal somatorioFGTS, BigDecimal somatorioINSS) {
		this.somatorioSalarios = somatorioSalarios;
		this.somatorioFGTS = somatorioFGTS;
		this.somatorioINSS = somatorioINSS;
	}
	
	public BigDecimal getSomatorioSalarios() {
		return somatorioSalarios;
	}
	public BigDecimal getSomatorioFGTS() {
		return somatorioFGTS;
	}
	public BigDecimal getSomatorioINSS() {
		return somatorioINSS;
	}

}
