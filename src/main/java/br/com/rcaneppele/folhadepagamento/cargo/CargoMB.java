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

	private static final String REDIRECT_PAGINA_CARGOS = "cargos?faces-redirect=true";

	@Inject
	private CargoRepository repository;
	
	@Inject
	private MensagensJSF msg;
	
	@Inject @Any
	private Instance<ValidadorCadastroCargo> validadoresCadastro;
	
	private Cargo cargo = new Cargo();
	private List<Cargo> todos;
	
	@Transactional
	public String cadastra() {
		try {
			validadoresCadastro.forEach(v -> v.valida(cargo));
			
			if (cargo.isSalvo()) {
				repository.atualiza(cargo);
				msg.adicionaSucesso("cargo.atualizacao.sucesso");
			} else {
				repository.cadastra(cargo);
				msg.adicionaSucesso("cargo.cadastro.sucesso");
			}

			return REDIRECT_PAGINA_CARGOS;
		} catch (ValidacaoException e) {
			msg.adicionaErro(e.getMessage());
			return "";
		}
	}
	
	@Transactional
	public String remove(Cargo selecionado) {
		repository.remove(selecionado);
		msg.adicionaSucesso("cargo.remocao.sucesso");
		
		return REDIRECT_PAGINA_CARGOS;
	}
	
	public List<Cargo> getTodos() {
		if (todos == null) {
			todos = repository.buscaTodosOrdenadosPeloNome();
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
