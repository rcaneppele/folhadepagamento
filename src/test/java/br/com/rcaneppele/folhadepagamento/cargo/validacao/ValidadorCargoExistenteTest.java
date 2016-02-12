package br.com.rcaneppele.folhadepagamento.cargo.validacao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;
import br.com.rcaneppele.folhadepagamento.cargo.CargoRepository;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

public class ValidadorCargoExistenteTest {
	
	@Mock
	private CargoRepository repository;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deveriaPermitirCadastrarCargoComNomeUnico() {
		Cargo dba = cargoDBA();
		
		Mockito.when(repository.buscaPorNome(dba.getNome())).thenReturn(null);
		
		ValidadorCargoExistente validador = new ValidadorCargoExistente(repository);
		validador.valida(dba);
	}

	@Test(expected = ValidacaoException.class)
	public void naoDeveriaPermitirCadastrarCargoComNomeRepetido() {
		Cargo dba = cargoDBA();
		
		Mockito.when(repository.buscaPorNome(dba.getNome())).thenReturn(new Cargo(1l));
		
		ValidadorCargoExistente validador = new ValidadorCargoExistente(repository);
		validador.valida(dba);
	}
	
	private Cargo cargoDBA() {
		Cargo dba = new Cargo();
		dba.setNome("DBA");
		return dba;
	}

}
