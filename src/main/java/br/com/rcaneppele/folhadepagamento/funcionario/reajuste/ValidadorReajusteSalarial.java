package br.com.rcaneppele.folhadepagamento.funcionario.reajuste;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;

@Named
@Dependent
public class ValidadorReajusteSalarial {
	
	private static final BigDecimal PERCENTUAL_MAXIMO_REAJUSTE = new BigDecimal("40");

	public boolean isFuncionarioPodeReceberReajuste(Funcionario funcionario, Reajuste reajuste) {
		
		return funcionarioJaPossuiTresMesesDeEmpresa(funcionario) && 
				funcionarioJaPossuiAoMenosSeisMesesDesdeOUltimoReajuste(funcionario) &&
				valorDoReajusteNaoUltrapassaLimiteDeQuarentaPorCentoDoSalario(funcionario, reajuste);
	}

	private boolean funcionarioJaPossuiTresMesesDeEmpresa(Funcionario funcionario) {
		Period periodoNaEmpresaAteADataDoReajuste = funcionario.getDataAdmissao().until(LocalDate.now());
		return periodoNaEmpresaAteADataDoReajuste.getMonths() >= 3;
	}

	private boolean funcionarioJaPossuiAoMenosSeisMesesDesdeOUltimoReajuste(Funcionario funcionario) {
		Reajuste ultimo = funcionario.getUltimoReajuste();
		return ultimo == null || ultimo.getData().until(LocalDate.now()).getMonths() >= 6;
	}
	
	private boolean valorDoReajusteNaoUltrapassaLimiteDeQuarentaPorCentoDoSalario(Funcionario funcionario, Reajuste reajuste) {
		BigDecimal percentualReajuste = reajuste.getValor().divide(funcionario.getSalario(), RoundingMode.DOWN).multiply(new BigDecimal("100"));
		
		return percentualReajuste.compareTo(PERCENTUAL_MAXIMO_REAJUSTE) <= 0;
	}
	
}
