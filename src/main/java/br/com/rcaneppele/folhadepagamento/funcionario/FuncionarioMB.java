package br.com.rcaneppele.folhadepagamento.funcionario;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;
import br.com.rcaneppele.folhadepagamento.cargo.CargoRepository;
import br.com.rcaneppele.folhadepagamento.funcionario.validacao.ValidadorCadastroFuncionario;
import br.com.rcaneppele.folhadepagamento.util.ValidacaoException;
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
	
	@Inject @Any
	private Instance<ValidadorCadastroFuncionario> validadoresCadastro;
	
	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> todos;
	private List<Cargo> cargos;
	
	@Transactional
	public void cadastra() {
		try {
			recuperaSalarioDoFuncionario();
		
			validadoresCadastro.forEach(v -> v.valida(funcionario));
		
			if (funcionario.isSalvo()) {
				repository.atualiza(funcionario);
				msg.adicionaSucesso("funcionario.atualizacao.sucesso");
			} else {
				repository.cadastra(funcionario);
				msg.adicionaSucesso("funcionario.cadastro.sucesso");
			}
			
			limpaFormulario();
			atualizaTabela();
		} catch (ValidacaoException e) {
			msg.adicionaErro(e.getMessage());
		}
	}
	
	private void recuperaSalarioDoFuncionario() {
		// Na alteracao o salario vem null, pois o campo na tela eh somente leitura
		if (funcionario.isSalvo()) {
			BigDecimal salarioAtual = repository.buscaSalarioAtualDoFuncionario(funcionario);
			funcionario.getDadosProfissionais().setSalario(salarioAtual);
		}
	}

	@Transactional
	public void remove(Funcionario selecionado) {
		repository.remove(selecionado);
		msg.adicionaSucesso("funcionario.remocao.sucesso");
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
