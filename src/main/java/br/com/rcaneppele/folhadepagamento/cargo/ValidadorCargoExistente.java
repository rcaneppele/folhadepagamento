package br.com.rcaneppele.folhadepagamento.cargo;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;

@Named
@Dependent
public class ValidadorCargoExistente {
	
	private final CargoRepository repository;
	
	@Inject
	public ValidadorCargoExistente(CargoRepository repository) {
		this.repository = repository;
	}
	
	public void valida(Cargo cargo) {
		Cargo existente = repository.buscaPorNome(cargo.getNome());
		if (existente != null && !cargo.equals(existente)) {
			throw new ValidacaoException("Já existe outro Cargo cadastrado com o nome informado!");
		}
	}

}
