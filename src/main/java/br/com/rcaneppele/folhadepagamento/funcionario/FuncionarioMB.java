package br.com.rcaneppele.folhadepagamento.funcionario;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;
import br.com.rcaneppele.folhadepagamento.cargo.CargoRepository;
import br.com.rcaneppele.folhadepagamento.util.jsf.MensagensJSF;

@Named
@RequestScoped
public class FuncionarioMB {

	@Inject
	private FuncionarioRepository repository;
	
	@Inject
	private CargoRepository cargoRepository;
	
	@Inject
	private MensagensJSF msg;
	
	@Inject
	private ValidadorFuncionarioExistente validadorFuncionarioExistente;
	
	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> todos;
	private List<Cargo> cargos;
	
	@Transactional
	public void cadastra() {
		if (validadorFuncionarioExistente.isFuncionarioJaCadastrado(funcionario)) {
			msg.adicionaMensagemErro("Já existe outro Funcionário cadastrado com o CPF/Matrícula informada!");
			return;
		}
		
		if (funcionario.isSalvo()) {
			repository.atualiza(funcionario);
			msg.adicionaMensagemSucesso("Funcionario atualizado com sucesso!");
		} else {
			repository.cadastra(funcionario);
			msg.adicionaMensagemSucesso("Funcionario cadastrado com sucesso!");
		}
		
		limpaFormulario();
		atualizaTabela();
	}
	
	@Transactional
	public void remove(Funcionario selecionado) {
		repository.remove(selecionado);
		msg.adicionaMensagemSucesso("Funcionario removido com sucesso!");
		atualizaTabela();
	}
	
	private void limpaFormulario() {
		this.funcionario = new Funcionario();
	}
	
	private void atualizaTabela() {
		this.todos = repository.buscaTodosOrdenadosPeloNome();
	}

	public List<Cargo> getCargos() {
		if (this.cargos == null) {
			this.cargos = cargoRepository.buscaTodosOrdenadosPeloNome();
		}
		return this.cargos;
	}
	public List<Funcionario> getTodos() {
		if (this.todos == null) {
			atualizaTabela();
		}
		return todos;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
