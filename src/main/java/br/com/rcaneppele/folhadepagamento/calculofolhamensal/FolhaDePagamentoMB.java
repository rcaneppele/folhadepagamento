package br.com.rcaneppele.folhadepagamento.calculofolhamensal;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rcaneppele.folhadepagamento.funcionario.Funcionario;
import br.com.rcaneppele.folhadepagamento.funcionario.FuncionarioRepository;

@Named
@RequestScoped
public class FolhaDePagamentoMB {
	
	private Integer mesEscolhido;
	private Integer anoEscolhido;
	
	private FolhadePagamento folhaDoMes;
	
	@Inject
	private FuncionarioRepository repository;
	
	@Inject
	private CalculadorFolhaDePagamento calculador;
	
	public void calculaFolhaDePagamento() {
		LocalDate data = LocalDate.of(anoEscolhido, mesEscolhido, 1);
		
		List<Funcionario> inclusos = repository.buscaTodosQueForamContratadosAntesDe(data);
		this.folhaDoMes = calculador.calcula(inclusos);
	}
	
	public Integer getMesEscolhido() {
		return mesEscolhido;
	}
	public void setMesEscolhido(Integer mesEscolhido) {
		this.mesEscolhido = mesEscolhido;
	}
	public Integer getAnoEscolhido() {
		return anoEscolhido;
	}
	public void setAnoEscolhido(Integer anoEscolhido) {
		this.anoEscolhido = anoEscolhido;
	}
	public FolhadePagamento getFolhaDoMes() {
		return folhaDoMes;
	}
	
}
