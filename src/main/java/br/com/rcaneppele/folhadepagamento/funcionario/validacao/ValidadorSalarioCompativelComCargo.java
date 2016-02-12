package br.com.rcaneppele.folhadepagamento.funcionario.validacao;

import java.math.BigDecimal;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

@Named
@Dependent
public class ValidadorSalarioCompativelComCargo implements ValidadorCadastroFuncionario {

	@Override
	public void valida(Funcionario funcionario) throws ValidacaoException {
		BigDecimal salarioMinimoDoCargo = funcionario.getCargo().getValorSalarioMinimo();
		BigDecimal salarioMaximoDoCargo = funcionario.getCargo().getValorSalarioMaximo();
		BigDecimal salarioDoFuncionario = funcionario.getSalario();
		
		if (salarioDoFuncionario.compareTo(salarioMinimoDoCargo) < 0 || salarioDoFuncionario.compareTo(salarioMaximoDoCargo) > 0) {
			throw new ValidacaoException("funcionario.validacao.erro.salarioCompativelComCargo");
		}
	}

}
