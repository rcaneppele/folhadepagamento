package br.com.rcaneppele.folhadepagamento.reajustesalarial.validacao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;
import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.reajustesalarial.Reajuste;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

public class ValidadorReajusteNaoUltrapassaFaixaSalarialDoCargoTest {

	@Test
	public void reajusteCujoValorNaoUltrapassaFaixaSalarialDoCargoPodeSerConcedido() {
		Funcionario joao = funcionario();
		
		Reajuste bonus = new Reajuste();
		bonus.setValor(new BigDecimal("1000.00"));
		
		ValidadorReajusteNaoUltrapassaFaixaSalarialDoCargo validador = new ValidadorReajusteNaoUltrapassaFaixaSalarialDoCargo();
		validador.valida(joao, bonus);
	}
	
	@Test
	public void reajusteCujoValorFiqueNoLimiteMaximoDaFaixaSalarialDoCargoPodeSerConcedido() {
		Funcionario joao = funcionario();
		
		Reajuste bonus = new Reajuste();
		bonus.setValor(new BigDecimal("4500.00"));
		
		ValidadorReajusteNaoUltrapassaFaixaSalarialDoCargo validador = new ValidadorReajusteNaoUltrapassaFaixaSalarialDoCargo();
		validador.valida(joao, bonus);
	}
	
	@Test(expected = ValidacaoException.class)
	public void reajusteCujoValorUltrapasseAFaixaSalarialDoCargoNaoPodeSerConcedido() {
		Funcionario joao = funcionario();
		
		Reajuste bonus = new Reajuste();
		bonus.setValor(new BigDecimal("4600.00"));
		
		ValidadorReajusteNaoUltrapassaFaixaSalarialDoCargo validador = new ValidadorReajusteNaoUltrapassaFaixaSalarialDoCargo();
		validador.valida(joao, bonus);
	}

	private Funcionario funcionario() {
		Funcionario joao = new Funcionario();
		joao.getDadosProfissionais().setSalario(new BigDecimal("3000.00"));
		joao.getDadosProfissionais().setCargo(desenvolvedor());
		return joao;
	}

	private Cargo desenvolvedor() {
		Cargo dev = new Cargo();
		dev.getFaixaSalarial().setValorMinimo(new BigDecimal("2500.00"));
		dev.getFaixaSalarial().setValorMaximo(new BigDecimal("7500.00"));
		return dev;
	}
	
}
