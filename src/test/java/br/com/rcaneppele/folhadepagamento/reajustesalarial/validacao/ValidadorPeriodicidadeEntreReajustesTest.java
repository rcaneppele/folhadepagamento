package br.com.rcaneppele.folhadepagamento.reajustesalarial.validacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.reajustesalarial.Reajuste;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

public class ValidadorPeriodicidadeEntreReajustesTest {

	@Test(expected = ValidacaoException.class)
	public void funcionarioNaoPodeReceberReajusteSeJaTiverRecebidoOutroHaMenosDe6Meses() {
		Reajuste bonus = bonus();
		
		Funcionario joao = funcionarioComSalario();
		joao.reajustaSalario(bonus);
		
		ValidadorPeriodicidadeEntreReajustes validador = new ValidadorPeriodicidadeEntreReajustes();
		validador.valida(joao, bonus);
	}

	@Test
	public void funcionarioSemReajustesPodeReceberSeuPrimeiroReajuste() {
		Reajuste bonus = bonus();
		Funcionario joao = funcionarioComSalario();
		
		ValidadorPeriodicidadeEntreReajustes validador = new ValidadorPeriodicidadeEntreReajustes();
		validador.valida(joao, bonus);
	}
	
	@Test
	public void funcionarioCujoUltimoReajusteFoiHaMaisDe6MesesPodeReceberNovoReajuste() {
		Reajuste bonus = bonus();
		bonus.setData(LocalDate.now().minusMonths(6));
		
		Funcionario joao = funcionarioComSalario();
		joao.reajustaSalario(bonus);
		
		ValidadorPeriodicidadeEntreReajustes validador = new ValidadorPeriodicidadeEntreReajustes();
		validador.valida(joao, bonus);
	}

	private Funcionario funcionarioComSalario() {
		Funcionario joao = new Funcionario();
		joao.getDadosProfissionais().setSalario(new BigDecimal("1000"));
		return joao;
	}
	
	private Reajuste bonus() {
		Reajuste bonus = new Reajuste();
		bonus.setValor(new BigDecimal("500.00"));
		return bonus;
	}
	
}
