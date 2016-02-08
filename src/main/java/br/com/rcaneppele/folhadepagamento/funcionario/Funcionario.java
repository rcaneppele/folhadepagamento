package br.com.rcaneppele.folhadepagamento.funcionario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;

@Entity
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	@Valid
	private DadosPessoais dadosPessoais = new DadosPessoais();
	
	@Embedded
	@Valid
	private DadosProfissionais dadosProfissionais = new DadosProfissionais();
	
	public boolean isSalvo() {
		return this.id != null;
	}
	
	public Cargo getCargo() {
		return this.dadosProfissionais.getCargo();
	}
	
	public BigDecimal getSalario() {
		return this.dadosProfissionais.getSalario();
	}
	
	public String getMatricula() {
		return this.getDadosProfissionais().getMatricula();
	}
	
	public LocalDate getDataAdmissao() {
		return this.dadosProfissionais.getDataAdmissao();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}
	public DadosProfissionais getDadosProfissionais() {
		return dadosProfissionais;
	}
	
}
