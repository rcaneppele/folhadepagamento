package br.com.rcaneppele.folhadepagamento.funcionario.validacao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.funcionario.FuncionarioRepository;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

public class ValidadorCPFUnicoTest {

	@Mock
	private FuncionarioRepository repository;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deveriaPermitirCadastrarFuncionarioComCPFUnico() {
		Funcionario joao = funcionarioComCPF();
		
		Mockito.when(repository.buscaPorCPF(joao.getDadosPessoais().getCpf())).thenReturn(null);
		
		ValidadorCPFUnico validador = new ValidadorCPFUnico(repository);
		validador.valida(joao);
	}

	@Test(expected = ValidacaoException.class)
	public void naoDeveriaPermitirCadastrarFuncionarioComCPFJaCadastrado() {
		Funcionario joao = funcionarioComCPF();
		
		Funcionario maria = new Funcionario();
		maria.setId(1l);
		
		Mockito.when(repository.buscaPorCPF(joao.getDadosPessoais().getCpf())).thenReturn(maria);
		
		ValidadorCPFUnico validador = new ValidadorCPFUnico(repository);
		validador.valida(joao);
	}
	
	private Funcionario funcionarioComCPF() {
		Funcionario joao = new Funcionario();
		joao.getDadosPessoais().setCpf("000.000.000-00");
		return joao;
	}
	
}
