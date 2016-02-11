package br.com.rcaneppele.folhadepagamento.cargo.validacao;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

public interface ValidadorCadastroCargo {

	public abstract void valida(Cargo cargo) throws ValidacaoException;

}
