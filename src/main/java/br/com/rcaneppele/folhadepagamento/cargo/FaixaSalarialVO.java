package br.com.rcaneppele.folhadepagamento.cargo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Embeddable
// R$880 => salario minimo 2016
public class FaixaSalarialVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "{valorMinimo.obrigatorio}")
	@DecimalMin(value = "880", message = "{valor.salario.invalido}")
	private BigDecimal valorMinimo;
	
	@NotNull(message = "{valorMaximo.obrigatorio}")
	@DecimalMin(value = "880", message = "{valor.salario.invalido}")
	private BigDecimal valorMaximo;
	
	public FaixaSalarialVO() {
	}

	public FaixaSalarialVO(BigDecimal valorMinimo, BigDecimal valorMaximo) {
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((valorMaximo == null) ? 0 : valorMaximo.hashCode());
		result = prime * result
				+ ((valorMinimo == null) ? 0 : valorMinimo.hashCode());
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
		FaixaSalarialVO other = (FaixaSalarialVO) obj;
		if (valorMaximo == null) {
			if (other.valorMaximo != null)
				return false;
		} else if (!valorMaximo.equals(other.valorMaximo))
			return false;
		if (valorMinimo == null) {
			if (other.valorMinimo != null)
				return false;
		} else if (!valorMinimo.equals(other.valorMinimo))
			return false;
		return true;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}
	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}
	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

}
