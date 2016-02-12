package br.com.rcaneppele.folhadepagamento.reajustesalarial;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.funcionario.FuncionarioRepository;
import br.com.rcaneppele.folhadepagamento.reajustesalarial.validacao.ValidadorCadastroReajuste;
import br.com.rcaneppele.folhadepagamento.util.jsf.MensagensJSF;

@Named
@RequestScoped
public class ReajusteMB {

	@Inject
	private FuncionarioRepository repository;
	
	@Inject
	private MensagensJSF msg;
	
	@Inject @Any
	private Instance<ValidadorCadastroReajuste> validadoresCadastro;
	
	private Funcionario funcionario;
	private Long idFuncionario;
	private Reajuste reajuste = new Reajuste();
	
	//Chamado ao entrar na tela de reajustes
	public String carregaReajustesDoFuncionario() {
		//Se entrar direto na pagina de reajustes sem passar o id do funcionario, volta pra pagina de funcionarios
		if (this.idFuncionario == null) {
			return "funcionarios";
		} 
		
		this.funcionario = repository.carregaFuncionarioComReajustes(idFuncionario);
		return "";
	}
	
	@Transactional
	public String cadastra() {
		try {
			this.funcionario = repository.carregaFuncionarioComReajustes(this.idFuncionario);
			
			validadoresCadastro.forEach(v -> v.valida(funcionario, reajuste));
			
			funcionario.reajustaSalario(reajuste);
			repository.atualiza(funcionario);
			
			msg.adicionaSucesso("reajuste.cadastro.sucesso");
			return "reajustes?faces-redirect=true&funcionario.id=" +idFuncionario;
		} catch (Exception e) {
			msg.adicionaErro(e.getMessage());
			return "";
		}
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
