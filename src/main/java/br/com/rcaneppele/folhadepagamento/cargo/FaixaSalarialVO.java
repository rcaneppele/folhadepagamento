package br.com.rcaneppele.folhadepagamento.cargo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class FaixaSalarialVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal valorInicial;
	private BigDecimal valorFinal;
	
	public FaixaSalarialVO() {
	}

	public FaixaSalarialVO(BigDecimal valorInicial, BigDecimal valorFinal) {
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((valorFinal == null) ? 0 : valorFinal.hashCode());
		result = prime * result
				+ ((valorInicial == null) ? 0 : valorInicial.hashCode());
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
		if (valorFinal == null) {
			if (other.valorFinal != null)
				return false;
		} else if (!valorFinal.equals(other.valorFinal))
			return false;
		if (valorInicial == null) {
			if (other.valorInicial != null)
				return false;
		} else if (!valorInicial.equals(other.valorInicial))
			return false;
		return true;
	}

	public BigDecimal getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}
	public BigDecimal getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

}
