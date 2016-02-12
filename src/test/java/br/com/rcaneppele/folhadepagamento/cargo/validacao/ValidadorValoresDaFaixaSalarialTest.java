package br.com.rcaneppele.folhadepagamento.cargo.validacao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

public class ValidadorValoresDaFaixaSalarialTest {
	
	@Test
	public void valorMinimoDaFaixaSalarialDoCargoPodeSerMenorQueOValorMaximo() {
		Cargo gestor = new Cargo();
		gestor.getFaixaSalarial().setValorMinimo(new BigDecimal("3000.00"));
		gestor.getFaixaSalarial().setValorMaximo(new BigDecimal("5000.00"));
		
		ValidadorValoresDaFaixaSalarial validador = new ValidadorValoresDaFaixaSalarial();
		validador.valida(gestor);
	}
	
	@Test
	public void valorMinimoDaFaixaSalarialDoCargoPodeSerIgualAoValorMaximo() {
		Cargo gestor = new Cargo();
		gestor.getFaixaSalarial().setValorMinimo(new BigDecimal("5000.00"));
		gestor.getFaixaSalarial().setValorMaximo(new BigDecimal("5000.00"));
		
		ValidadorValoresDaFaixaSalarial validador = new ValidadorValoresDaFaixaSalarial();
		validador.valida(gestor);
	}
	
	@Test(expected = ValidacaoException.class)
	public void valorMinimoDaFaixaSalarialDoCargoNaoPodeMaiorQueOValorMaximo() {
		Cargo gestor = new Cargo();
		gestor.getFaixaSalarial().setValorMinimo(new BigDecimal("5000.00"));
		gestor.getFaixaSalarial().setValorMaximo(new BigDecimal("3000.00"));
		
		ValidadorValoresDaFaixaSalarial validador = new ValidadorValoresDaFaixaSalarial();
		validador.valida(gestor);
	}

}
