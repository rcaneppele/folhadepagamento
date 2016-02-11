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

	@Inject
	private FuncionarioRepository repository;
	
	@Inject
	private MensagensJSF msg;
	
	@Inject
	private ValidadorReajusteSalarial validadorReajusteSalarial;
	
	private Funcionario funcionario;
	private Long idFuncionario;
	private Reajuste reajuste = new Reajuste();
	
	public String carregaReajustesDoFuncionario(Long idFuncionario) {
		this.funcionario = repository.carregaFuncionarioComReajustes(idFuncionario);
		this.idFuncionario = idFuncionario;
		return "reajustes";
	}
	
	@Transactional
	public void cadastra() {
		try {
			this.funcionario = repository.carregaFuncionarioComReajustes(this.idFuncionario);
			validadorReajusteSalarial.valida(funcionario, reajuste);
			funcionario.reajustaSalario(reajuste);
			repository.atualiza(funcionario);
			
			msg.adicionaMensagemSucesso("Reajuste cadastrado com sucesso!");
			limpaFormulario();
		} catch (Exception e) {
			msg.adicionaMensagemErro(e.getMessage());
		}
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
