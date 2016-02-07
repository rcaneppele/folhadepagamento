package br.com.rcaneppele.folhadepagamento.cargo;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.rcaneppele.folhadepagamento.util.jsf.MensagensJSF;

@Named
@RequestScoped
public class CargoMB {

	@Inject
	private CargoRepository repository;
	
	@Inject
	private MensagensJSF msg;
	
	@Inject
	private ValidadorCargoExistente validadorCargoExistente;
	
	private Cargo cargo = new Cargo();
	private List<Cargo> todos;
	
	@Transactional
	public void cadastra() {
		if (validadorCargoExistente.isCargoJaCadastrado(cargo)) {
			msg.adicionaMensagemErro("Já existe outro Cargo cadastrado com o nome informado!");
			return;
		}
		
		if (cargo.isSalvo()) {
			repository.atualiza(cargo);
			msg.adicionaMensagemSucesso("Cargo atualizado com sucesso!");
		} else {
			repository.cadastra(cargo);
			msg.adicionaMensagemSucesso("Cargo cadastrado com sucesso!");
		}
		
		limpaFormulario();
		atualizaTabela();
	}
	
	@Transactional
	public void remove(Cargo selecionado) {
		repository.remove(selecionado);
		msg.adicionaMensagemSucesso("Cargo removido com sucesso!");
		atualizaTabela();
	}
	
	private void limpaFormulario() {
		this.cargo = new Cargo();
	}
	
	private void atualizaTabela() {
		this.todos = repository.buscaTodos();
	}

	public List<Cargo> getTodos() {
		if (this.todos == null) {
			atualizaTabela();
		}
		return todos;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
}
