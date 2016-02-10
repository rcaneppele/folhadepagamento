package br.com.rcaneppele.folhadepagamento.funcionario.reajuste;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.funcionario.FuncionarioRepository;
import br.com.rcaneppele.folhadepagamento.util.jsf.MensagensJSF;

@Named
@RequestScoped
public class ReajusteMB {

	private Funcionario funcionario;
	private Long idFuncionario;
	private Reajuste reajuste = new Reajuste();
	
	@Inject
	private FuncionarioRepository repository;
	
	@Inject
	private MensagensJSF msg;
	
	@Inject
	private ValidadorReajusteSalarial validadorReajusteSalarial;
	
	public String carregaReajustesDoFuncionario(Long idFuncionario) {
		this.funcionario = repository.carregaFuncionarioComReajustes(idFuncionario);
		this.idFuncionario = idFuncionario;
		return "reajustes";
	}
	
	@Transactional
	public void cadastra() {
		this.funcionario = repository.carregaFuncionarioComReajustes(this.idFuncionario);
		
		if (!validadorReajusteSalarial.isFuncionarioPodeReceberReajuste(funcionario, reajuste)) {
			msg.adicionaMensagemErro("Funcionário não pode receber reajuste por não possuir 3 meses de empresa ou por ainda não ter 6 meses desde o último reajuste ou pelo valor do reajuste ser superior ao limite de 40% do salário!");
			return;
		}
		
		funcionario.reajustaSalario(reajuste);
		repository.atualiza(funcionario);
		
		msg.adicionaMensagemSucesso("Reajuste cadastrado com sucesso!");
		limpaFormulario();
	}
	
	private void limpaFormulario() {
		this.reajuste = new Reajuste();
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public Long getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public Reajuste getReajuste() {
		return reajuste;
	}
	
}
