package br.com.rcaneppele.folhadepagamento.reajustesalarial.validacao;

import java.time.LocalDate;
import java.time.Period;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.reajustesalarial.Reajuste;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

@Named
@Dependent
public class ValidadorPeriodoDeExperiencia implements ValidadorCadastroReajuste {
	
	@Override
	public void valida(Funcionario funcionario, Reajuste reajuste) throws ValidacaoException {
		Period periodoNaEmpresa = funcionario.getDataAdmissao().until(LocalDate.now());
		if (periodoNaEmpresa.toTotalMonths() < 3) {
			throw new ValidacaoException("reajuste.validacao.erro.periodoExperiencia");
		}
	}

}
