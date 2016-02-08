package br.com.rcaneppele.folhadepagamento.funcionario;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class DadosPessoais implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "{nome.obrigatorio}") 
	@Size(min = 1, message = "{nome.obrigatorio}")
	private String nome;
	
	@NotNull(message = "{cpf.obrigatorio}") 
	@Size(min = 1, message = "{cpf.obrigatorio}")
	private String cpf;
	
	@NotNull(message = "{email.obrigatorio}") 
	@Size(min = 1, message = "{email.obrigatorio}")
	private String email;
	
	@NotNull(message = "{telefone.obrigatorio}") 
	@Size(min = 1, message = "{telefone.obrigatorio}")
	private String telefone;
	
	public DadosPessoais() {
	}

	public DadosPessoais(String nome, String cpf, String email, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
