package br.com.rcaneppele.folhadepagamento.cargo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cargo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "{nome.obrigatorio}") 
	@Size(min = 1, message = "{nome.obrigatorio}")
	private String nome;
	
	@Embedded
	@Valid
	private FaixaSalarial faixaSalarial = new FaixaSalarial();
	
	public Cargo() {
	}
	
	public Cargo(Long id) {
		this.id = id;
	}

	public boolean isSalvo() {
		return this.id != null;
	}
	
	public BigDecimal getValorSalarioMinimo() {
		return this.faixaSalarial.getValorMinimo();
	}
	
	public BigDecimal getValorSalarioMaximo() {
		return this.faixaSalarial.getValorMaximo();
	}
	
	@Override
	public String toString() {
		return this.nome +faixaSalarial.toString();
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
		Cargo other = (Cargo) obj;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public FaixaSalarial getFaixaSalarial() {
		return faixaSalarial;
	}
	
}
