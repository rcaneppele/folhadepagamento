package br.com.rcaneppele.folhadepagamento.funcionario;

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
		Funcionario existenteComMesmoCPF = repository.buscaPorCPF(funcionario.getDadosPessoais().getCpf());
		if (existenteComMesmoCPF != null && !funcionario.equals(existenteComMesmoCPF)) {
			return true;
		}
		
		Funcionario existenteComMesmaMatricula = repository.buscaPorMatricula(funcionario.getDadosProfissionais().getMatricula());
		if (existenteComMesmaMatricula != null && !funcionario.equals(existenteComMesmaMatricula)) {
			return true;
		}
		
		return false;
	}


}
