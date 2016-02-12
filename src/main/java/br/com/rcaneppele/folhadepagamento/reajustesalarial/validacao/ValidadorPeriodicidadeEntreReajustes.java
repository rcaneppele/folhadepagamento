package br.com.rcaneppele.folhadepagamento.reajustesalarial.validacao;

import java.time.LocalDate;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.reajustesalarial.Reajuste;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

@Named
@Dependent
public class ValidadorPeriodicidadeEntreReajustes implements ValidadorCadastroReajuste {
	
	@Override
	public void valida(Funcionario funcionario, Reajuste reajuste) throws ValidacaoException {
		Reajuste ultimo = funcionario.getUltimoReajuste();
		if (ultimo != null && ultimo.getData().until(LocalDate.now()).toTotalMonths() < 6) {
			throw new ValidacaoException("reajuste.validacao.erro.periodicidade");
		}
	}
	
}
