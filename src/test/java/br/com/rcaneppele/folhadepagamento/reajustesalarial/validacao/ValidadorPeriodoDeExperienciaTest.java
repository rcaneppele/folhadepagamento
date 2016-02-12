package br.com.rcaneppele.folhadepagamento.reajustesalarial.validacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.reajustesalarial.Reajuste;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

public class ValidadorPeriodoDeExperienciaTest {

	@Test(expected = ValidacaoException.class)
	public void funcionarioEmPeriodoDeExperienciaNaoPodeReceberReajustes() {
		Funcionario recemContratado = new Funcionario();
		recemContratado.getDadosProfissionais().setDataAdmissao(LocalDate.now().minusDays(10));
		
		ValidadorPeriodoDeExperiencia validador = new ValidadorPeriodoDeExperiencia();
		validador.valida(recemContratado, bonus());
	}
	
	@Test
	public void funcionarioComMaisDe3MesesPodeReceberReajustes() {
		Funcionario velho = new Funcionario();
		velho.getDadosProfissionais().setDataAdmissao(LocalDate.now().minusMonths(6));
		
		ValidadorPeriodoDeExperiencia validador = new ValidadorPeriodoDeExperiencia();
		validador.valida(velho, bonus());
	}
	
	@Test
	public void funcionarioQueAcabouDeCompletar3MesesJaPodeReceberReajustes() {
		Funcionario quaseVelho = new Funcionario();
		quaseVelho.getDadosProfissionais().setDataAdmissao(LocalDate.now().minusMonths(3));
		
		ValidadorPeriodoDeExperiencia validador = new ValidadorPeriodoDeExperiencia();
		validador.valida(quaseVelho, bonus());
	}
	
	private Reajuste bonus() {
		Reajuste bonus = new Reajuste();
		bonus.setValor(new BigDecimal("500"));
		return bonus;
	}
	
}
