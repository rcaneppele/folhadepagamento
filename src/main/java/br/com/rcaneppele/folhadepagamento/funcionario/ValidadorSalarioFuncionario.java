package br.com.rcaneppele.folhadepagamento.funcionario;

import java.math.BigDecimal;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

@Named
@Dependent
public class ValidadorSalarioFuncionario {

	public void valida(Funcionario funcionario) {
		BigDecimal salarioMinimoDoCargo = funcionario.getCargo().getValorSalarioMinimo();
		BigDecimal salarioMaximoDoCargo = funcionario.getCargo().getValorSalarioMaximo();
		BigDecimal salarioDoFuncionario = funcionario.getSalario();
		
		if (salarioDoFuncionario.compareTo(salarioMinimoDoCargo) < 0 || salarioDoFuncionario.compareTo(salarioMaximoDoCargo) > 0) {
			throw new ValidacaoException("Sálario do funcionário não está de acordo com a faixa salarial do cargo escolhido!");
		}
	}

}
