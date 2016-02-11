package br.com.rcaneppele.folhadepagamento.funcionario.validacao;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

public interface ValidadorCadastroFuncionario {

	public abstract void valida(Funcionario funcionario) throws ValidacaoException;

}
