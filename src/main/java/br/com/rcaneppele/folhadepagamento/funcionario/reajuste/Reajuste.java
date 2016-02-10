package br.com.rcaneppele.folhadepagamento.funcionario.reajuste;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Reajuste implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDate data = LocalDate.now();
	
	@NotNull(message = "{valor.obrigatorio}")
	private BigDecimal valor;
	
	@NotNull(message = "{justificativa.obrigatoria}") 
	@Size(min = 1, message = "{justificativa.obrigatoria}")
	private String justificativa;
	
	public LocalDate getData() {
		return data;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
}
