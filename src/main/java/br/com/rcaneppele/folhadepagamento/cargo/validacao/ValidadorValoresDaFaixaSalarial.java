package br.com.rcaneppele.folhadepagamento.cargo.validacao;

import java.math.BigDecimal;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

@Named
@Dependent
public class ValidadorValoresDaFaixaSalarial implements ValidadorCadastroCargo {
	
	@Override
	public void valida(Cargo cargo) throws ValidacaoException {
		BigDecimal valorMinimo = cargo.getFaixaSalarial().getValorMinimo();
		BigDecimal valorMaximo = cargo.getFaixaSalarial().getValorMaximo();
		
		if (valorMinimo.compareTo(valorMaximo) > 0) {
			throw new ValidacaoException("Valor Máximo deve ser maior ou igual ao Valor Mínimo!");
		}
	}

}
