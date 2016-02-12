package br.com.rcaneppele.folhadepagamento.funcionario.validacao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;
import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

public class ValidadorSalarioCompativelComCargoTest {
	
	@Test
	public void deveriaPermitirSalarioComValorDentroDaFaixaSalarialDoCargo() {
		Funcionario joao = new Funcionario();
		joao.getDadosProfissionais().setCargo(cargoComFaixaSalarialEntre2500E5000Reais());
		joao.getDadosProfissionais().setSalario(new BigDecimal("3000.00"));
		
		ValidadorSalarioCompativelComCargo validador = new ValidadorSalarioCompativelComCargo();
		validador.valida(joao);
	}
	
	@Test
	public void deveriaPermitirSalarioComValorNoLimiteMinimoDaFaixaSalarialDoCargo() {
		Funcionario joao = new Funcionario();
		joao.getDadosProfissionais().setCargo(cargoComFaixaSalarialEntre2500E5000Reais());
		joao.getDadosProfissionais().setSalario(new BigDecimal("2500.00"));
		
		ValidadorSalarioCompativelComCargo validador = new ValidadorSalarioCompativelComCargo();
		validador.valida(joao);
	}
	
	@Test
	public void deveriaPermitirSalarioComValorNoLimiteMaximoDaFaixaSalarialDoCargo() {
		Funcionario joao = new Funcionario();
		joao.getDadosProfissionais().setCargo(cargoComFaixaSalarialEntre2500E5000Reais());
		joao.getDadosProfissionais().setSalario(new BigDecimal("5000.00"));
		
		ValidadorSalarioCompativelComCargo validador = new ValidadorSalarioCompativelComCargo();
		validador.valida(joao);
	}
	
	@Test(expected = ValidacaoException.class)
	public void naoDeveriaPermitirSalarioComValorAbaixoDoLimiteMinimoDaFaixaSalarialDoCargo() {
		Funcionario joao = new Funcionario();
		joao.getDadosProfissionais().setCargo(cargoComFaixaSalarialEntre2500E5000Reais());
		joao.getDadosProfissionais().setSalario(new BigDecimal("1500.00"));
		
		ValidadorSalarioCompativelComCargo validador = new ValidadorSalarioCompativelComCargo();
		validador.valida(joao);
	}
	
	@Test(expected = ValidacaoException.class)
	public void naoDeveriaPermitirSalarioComValorAcimaDoLimiteMaximoDaFaixaSalarialDoCargo() {
		Funcionario joao = new Funcionario();
		joao.getDadosProfissionais().setCargo(cargoComFaixaSalarialEntre2500E5000Reais());
		joao.getDadosProfissionais().setSalario(new BigDecimal("7000.00"));
		
		ValidadorSalarioCompativelComCargo validador = new ValidadorSalarioCompativelComCargo();
		validador.valida(joao);
	}
	
	private Cargo cargoComFaixaSalarialEntre2500E5000Reais() {
		Cargo analista = new Cargo();
		analista.getFaixaSalarial().setValorMinimo(new BigDecimal("2500.00"));
		analista.getFaixaSalarial().setValorMaximo(new BigDecimal("5000.00"));
		return analista;
	}

}
