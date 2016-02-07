package br.com.rcaneppele.folhadepagamento.util.jsf;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class MensagensJSF {
	
	@Inject
	private FacesContext context;

	public void adicionaMensagemSucesso(String mensagem) {
		adicionaMensagem(mensagem, FacesMessage.SEVERITY_INFO);
	}
	
	public void adicionaMensagemErro(String mensagem) {
		adicionaMensagem(mensagem, FacesMessage.SEVERITY_ERROR);
	}
	
	private void adicionaMensagem(String mensagem, Severity tipo) {
		FacesMessage jsfMessage = new FacesMessage(tipo, mensagem, null);
		context.addMessage(null, jsfMessage);
	}
	
}
