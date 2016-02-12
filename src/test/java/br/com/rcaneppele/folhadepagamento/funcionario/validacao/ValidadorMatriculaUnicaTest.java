package br.com.rcaneppele.folhadepagamento.funcionario.validacao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.funcionario.FuncionarioRepository;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

public class ValidadorMatriculaUnicaTest {

	@Mock
	private FuncionarioRepository repository;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deveriaPermitirCadastrarFuncionarioComMatriculaUnica() {
		Funcionario joao = funcionarioComMatricula();
		
		Mockito.when(repository.buscaPorCPF(joao.getDadosPessoais().getCpf())).thenReturn(null);
		
		ValidadorMatriculaUnica validador = new ValidadorMatriculaUnica(repository);
		validador.valida(joao);
	}

	@Test(expected = ValidacaoException.class)
	public void naoDeveriaPermitirCadastrarFuncionarioComMatriculaJaCadastrada() {
		Funcionario joao = funcionarioComMatricula();
		
		Funcionario maria = new Funcionario();
		maria.setId(1l);
		
		Mockito.when(repository.buscaPorMatricula(joao.getMatricula())).thenReturn(maria);
		
		ValidadorMatriculaUnica validador = new ValidadorMatriculaUnica(repository);
		validador.valida(joao);
	}
	
	private Funcionario funcionarioComMatricula() {
		Funcionario joao = new Funcionario();
		joao.getDadosProfissionais().setMatricula("M-001");
		return joao;
	}
	
}
