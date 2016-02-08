package br.com.rcaneppele.folhadepagamento.util.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.rcaneppele.folhadepagamento.cargo.Cargo;
import br.com.rcaneppele.folhadepagamento.cargo.CargoRepository;

@FacesConverter("CargoConverter")
public class CargoConverter implements Converter {
	
	@Inject
	private CargoRepository repository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		
		return repository.buscaPorId(Long.parseLong(value));
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
