package br.com.rcaneppele.folhadepagamento.calculofolhamensal;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;

@Named
@Dependent
public class CalculadorFolhaDePagamento {

	public FolhadePagamento calcula(List<Funcionario> inclusos) {
		BigDecimal somatorioSalarios = calculaSomatorioSalarios(inclusos);
		BigDecimal somatorioFGTS = calculaSomatorioFGTS(inclusos);
		BigDecimal somatorioINSS = calculaSomatorioINSS(inclusos);
		
		return new FolhadePagamento(somatorioSalarios, somatorioFGTS, somatorioINSS);
	}

	private BigDecimal calculaSomatorioSalarios(List<Funcionario> inclusos) {
		return inclusos.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private BigDecimal calculaSomatorioFGTS(List<Funcionario> inclusos) {
		return inclusos.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, (somatorio, atual) -> somatorio.add(atual.multiply(new BigDecimal("0.08"))));
	}

	private BigDecimal calculaSomatorioINSS(List<Funcionario> inclusos) {
		return inclusos.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, (somatorio, atual) -> somatorio.add(atual.multiply(new BigDecimal("0.2"))));
	}
	
}
