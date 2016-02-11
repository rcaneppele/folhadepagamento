package br.com.rcaneppele.folhadepagamento.reajustesalarial.validacao;

import java.math.BigDecimal;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.reajustesalarial.Reajuste;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

@Named
@Dependent
public class ValidadorReajusteNaoUltrapassaFaixaSalarialDoCargo implements ValidadorCadastroReajuste {
	
	@Override
	public void valida(Funcionario funcionario, Reajuste reajuste) throws ValidacaoException {
		BigDecimal salarioMaximoDoCargo = funcionario.getCargo().getValorSalarioMaximo();
		BigDecimal salarioReajustado = funcionario.getSalario().add(reajuste.getValor());
		
		if (salarioReajustado.compareTo(salarioMaximoDoCargo) > 0) {
			throw new ValidacaoException("Reajuste não pode ser concedido pois o novo valor do salário ultrapassaria a faixa salarial do cargo!");
		}
	}

}
