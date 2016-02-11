package br.com.rcaneppele.folhadepagamento.reajustesalarial.validacao;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.reajustesalarial.Reajuste;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

@Named
@Dependent
public class ValidadorPercentualDoReajuste implements ValidadorCadastroReajuste {
	
	private static final BigDecimal PERCENTUAL_MAXIMO_REAJUSTE = new BigDecimal("40");
	
	@Override
	public void valida(Funcionario funcionario, Reajuste reajuste) throws ValidacaoException {
		BigDecimal percentualReajuste = calculaPercentualDoReajuste(funcionario, reajuste);
		
		if (percentualReajuste.compareTo(PERCENTUAL_MAXIMO_REAJUSTE) > 0) {
			throw new ValidacaoException("Valor do reajuste não pode ser superior a 40% do salário do funcionário!");
		}
	}

	private BigDecimal calculaPercentualDoReajuste(Funcionario funcionario, Reajuste reajuste) {
		return reajuste.getValor().divide(funcionario.getSalario(), RoundingMode.DOWN).multiply(new BigDecimal("100"));
	}

}
