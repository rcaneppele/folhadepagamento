package br.com.rcaneppele.folhadepagamento.reajustesalarial.validacao;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.reajustesalarial.Reajuste;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

public interface ValidadorCadastroReajuste {

	public abstract void valida(Funcionario funcionario, Reajuste reajuste) throws ValidacaoException;

}
