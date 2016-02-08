package br.com.rcaneppele.folhadepagamento.util.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;

@FacesConverter("CargoConverter")
public class CargoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		
		return new Cargo(Long.parseLong(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		
		Cargo cargo = (Cargo) value;
		return cargo.getId().toString();
	}

}
