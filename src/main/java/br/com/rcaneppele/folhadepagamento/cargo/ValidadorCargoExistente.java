package br.com.rcaneppele.folhadepagamento.cargo;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public class ValidadorCargoExistente {
	
	private final CargoRepository repository;
	
	@Inject
	public ValidadorCargoExistente(CargoRepository repository) {
		this.repository = repository;
	}
	
	public boolean isCargoJaCadastrado(Cargo cargo) {
		Cargo existente = repository.buscaPorNome(cargo.getNome());
		if (existente != null && !cargo.equals(existente)) {
			return true;
		}
		
		return false;
	}


}
