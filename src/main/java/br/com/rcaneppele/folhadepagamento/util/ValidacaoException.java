package br.com.rcaneppele.folhadepagamento.util;

public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoException(String messageKey) {
		super(messageKey);
	}

}
