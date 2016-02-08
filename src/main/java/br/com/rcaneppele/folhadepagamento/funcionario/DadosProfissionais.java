package br.com.rcaneppele.folhadepagamento.funcionario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;

@Embeddable
public class DadosProfissionais implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "{matricula.obrigatoria}") 
	@Size(min = 1, message = "{matricula.obrigatoria}")
	private String matricula;
	
	@NotNull(message = "{salario.obrigatorio}") 
	private BigDecimal salario;
	
	@NotNull(message = "{cargo.obrigatorio}") 
	@ManyToOne
	private Cargo cargo;
	
	@NotNull(message = "{dataAdmissao.obrigatoria}") 
	private LocalDate dataAdmissao;
	
	public DadosProfissionais() {
	}
	
	public DadosProfissionais(String matricula, BigDecimal salario, Cargo cargo, LocalDate dataAdmissao) {
		this.matricula = matricula;
		this.salario = salario;
		this.cargo = cargo;
		this.dataAdmissao = dataAdmissao;
	}

	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
}
