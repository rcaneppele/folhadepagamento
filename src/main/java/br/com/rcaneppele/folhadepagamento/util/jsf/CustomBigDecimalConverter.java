package br.com.rcaneppele.folhadepagamento.util.jsf;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("CustomBigDecimalConverter")
public class CustomBigDecimalConverter implements Converter {

	/**
	 * De: String(R$ 1.500,00) -> Para: BigDecimal(1500.00)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		
		return new BigDecimal(value.replace(".", "").replace(",", "."));
	}

	/**
	 * De: BigDecimal(1500.00) -> Para: String(R$ 1.500,00)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		
		DecimalFormat formatter = new DecimalFormat("#,##0.00");
		formatter.setParseBigDecimal(true);
		String formatado = formatter.format(value);
		
		return "R$ " +formatado;
	}

}
