package br.com.rcaneppele.folhadepagamento.util.jsf;

import java.util.ResourceBundle;

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
	
	public void adicionaSucesso(String key) {
		adicionaMensagem(key, FacesMessage.SEVERITY_INFO);
	}
	
	public void adicionaErro(String key) {
		adicionaMensagem(key, FacesMessage.SEVERITY_ERROR);
	}
	
	private void adicionaMensagem(String key, Severity tipo) {
		String mensagem = getResourceBundle().getString(key);
		
		FacesMessage jsfMessage = new FacesMessage(tipo, mensagem, null);
		context.addMessage(null, jsfMessage);
		
		//Para nao perder as mensagens em caso de REDIRECT
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	private ResourceBundle getResourceBundle() {
		return context.getApplication().getResourceBundle(context, "bundle");
	}
	
}
