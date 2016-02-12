package br.com.rcaneppele.folhadepagamento.cargo.validacao;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;
import br.com.rcaneppele.folhadepagamento.cargo.CargoRepository;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

@Named
@Dependent
public class ValidadorCargoExistente implements ValidadorCadastroCargo {
	
	private final CargoRepository repository;
	
	@Inject
	public ValidadorCargoExistente(CargoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void valida(Cargo cargo) throws ValidacaoException {
		Cargo existente = repository.buscaPorNome(cargo.getNome());
		if (existente != null && !cargo.equals(existente)) {
			throw new ValidacaoException("cargo.validacao.erro.existente");
		}
	}

}
