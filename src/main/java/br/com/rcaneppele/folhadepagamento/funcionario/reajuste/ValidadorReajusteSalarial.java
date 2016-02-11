package br.com.rcaneppele.folhadepagamento.funcionario.reajuste;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

@Named
@Dependent
public class ValidadorReajusteSalarial {
	
	private static final BigDecimal PERCENTUAL_MAXIMO_REAJUSTE = new BigDecimal("40");

	public void valida(Funcionario funcionario, Reajuste reajuste) {
		if (funcionarioAindaEstaEmPeriodoDeExperiencia(funcionario)) {
			throw new ValidacaoException("Funcionário não pode receber reajuste pois ainda está em período de experiência!");
		}
		
		if (funcionarioRecebeuReajusteHaMenosDeSeisMeses(funcionario)) {
			throw new ValidacaoException("Funcionário não pode receber reajuste por já ter recebido outro há menos de 06 meses!");
		}
		
		if (valorDoReajusteUltrapassaLimiteDeQuarentaPorCentoDoSalario(funcionario, reajuste)) {
			throw new ValidacaoException("Valor do reajuste não pode ser superior a 40% do salário do funcionário!");
		}
	}

	private boolean funcionarioAindaEstaEmPeriodoDeExperiencia(Funcionario funcionario) {
		Period periodoNaEmpresa = funcionario.getDataAdmissao().until(LocalDate.now());
		return periodoNaEmpresa.getMonths() < 3;
	}

	private boolean funcionarioRecebeuReajusteHaMenosDeSeisMeses(Funcionario funcionario) {
		Reajuste ultimo = funcionario.getUltimoReajuste();
		return ultimo != null && ultimo.getData().until(LocalDate.now()).getMonths() < 6;
	}
	
	private boolean valorDoReajusteUltrapassaLimiteDeQuarentaPorCentoDoSalario(Funcionario funcionario, Reajuste reajuste) {
		BigDecimal percentualReajuste = reajuste.getValor().divide(funcionario.getSalario(), RoundingMode.DOWN).multiply(new BigDecimal("100"));
		
		return percentualReajuste.compareTo(PERCENTUAL_MAXIMO_REAJUSTE) > 0;
	}
	
}
