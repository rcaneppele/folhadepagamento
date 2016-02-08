package br.com.rcaneppele.folhadepagamento.funcionario;

import java.math.BigDecimal;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class ValidadorSalarioFuncionario {

	public boolean isSalarioCompativelComOCargo(Funcionario funcionario) {
		BigDecimal salarioMinimo = funcionario.getCargo().getValorSalarioMinimo();
		BigDecimal salarioMaximo = funcionario.getCargo().getValorSalarioMaximo();

		BigDecimal salarioEscolhido = funcionario.getSalario();

		return isSalarioDoFuncionarioEstaEntreAFaixaSalarialDoCargo(salarioEscolhido, salarioMinimo, salarioMaximo);
	}

	private boolean isSalarioDoFuncionarioEstaEntreAFaixaSalarialDoCargo(BigDecimal salarioEscolhido, BigDecimal salarioMinimo, BigDecimal salarioMaximo) {
		return salarioEscolhido.compareTo(salarioMinimo) >= 0 && salarioEscolhido.compareTo(salarioMaximo) <= 0;
	}

}
