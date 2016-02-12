package br.com.rcaneppele.folhadepagamento.reajustesalarial.validacao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.reajustesalarial.Reajuste;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

public class ValidadorPercentualDoReajusteTest {

	@Test
	public void reajustePodeSerConcedidoSeValorEstiverAbaixoDoLimite() {
		Funcionario joao = funcionarioComSalarioDe3000Reais();
		
		Reajuste bonusDeDezPorCento = new Reajuste();
		bonusDeDezPorCento.setValor(new BigDecimal("300"));
		
		ValidadorPercentualDoReajuste validador = new ValidadorPercentualDoReajuste();
		validador.valida(joao, bonusDeDezPorCento);
	}
	
	@Test
	public void reajustePodeSerConcedidoSeValorEstiverNoLimite() {
		Funcionario joao = funcionarioComSalarioDe3000Reais();
		
		Reajuste bonusDeQuarentaPorCento = new Reajuste();
		bonusDeQuarentaPorCento.setValor(new BigDecimal("1200"));
		
		ValidadorPercentualDoReajuste validador = new ValidadorPercentualDoReajuste();
		validador.valida(joao, bonusDeQuarentaPorCento);
	}

	@Test(expected = ValidacaoException.class)
	public void reajusteNaoPodeSerConcedidoSeValorEstiverAcimaDoLimite() {
		Funcionario joao = funcionarioComSalarioDe3000Reais();
		
		Reajuste bonusDeCinquentaPorCento = new Reajuste();
		bonusDeCinquentaPorCento.setValor(new BigDecimal("1500"));
		
		ValidadorPercentualDoReajuste validador = new ValidadorPercentualDoReajuste();
		validador.valida(joao, bonusDeCinquentaPorCento);
	}
	
	private Funcionario funcionarioComSalarioDe3000Reais() {
		Funcionario joao = new Funcionario();
		joao.getDadosProfissionais().setSalario(new BigDecimal("3000"));
		return joao;
	}
	
}
