package br.com.rcaneppele.folhadepagamento.funcionario;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public class ValidadorFuncionarioExistente {
	
	private final FuncionarioRepository repository;
	
	@Inject
	public ValidadorFuncionarioExistente(FuncionarioRepository repository) {
		this.repository = repository;
	}
	
	public boolean isFuncionarioJaCadastrado(Funcionario funcionario) {
		List<Funcionario> encontrados = repository.buscaPorCPFOuMatricula(funcionario.getDadosPessoais().getCpf(), funcionario.getDadosProfissionais().getMatricula());
		if (encontrados.isEmpty() || (encontrados.size() == 1 && encontrados.contains(funcionario))) {
			return false;
		}
		
		return true;
	}


}
