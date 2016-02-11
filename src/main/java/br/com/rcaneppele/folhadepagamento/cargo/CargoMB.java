package br.com.rcaneppele.folhadepagamento.cargo;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.rcaneppele.folhadepagamento.cargo.validacao.ValidadorCadastroCargo;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;
import br.com.rcaneppele.folhadepagamento.util.jsf.MensagensJSF;

@Named
@RequestScoped
public class CargoMB {

	@Inject
	private CargoRepository repository;
	
	@Inject
	private MensagensJSF msg;
	
	@Inject @Any
	private Instance<ValidadorCadastroCargo> validadoresCadastro;
	
	private Cargo cargo = new Cargo();
	private List<Cargo> todos;
	
	@Transactional
	public void cadastra() {
		try {
			validadoresCadastro.forEach(v -> v.valida(cargo));
			
			if (cargo.isSalvo()) {
				repository.atualiza(cargo);
				msg.adicionaMensagemSucesso("Cargo atualizado com sucesso!");
			} else {
				repository.cadastra(cargo);
				msg.adicionaMensagemSucesso("Cargo cadastrado com sucesso!");
			}
			
			limpaFormulario();
			atualizaTabela();
		} catch (ValidacaoException e) {
			msg.adicionaMensagemErro(e.getMessage());
		}
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
		this.todos = repository.buscaTodosOrdenadosPeloNome();
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
