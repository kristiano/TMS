package tcc.br.com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Leslie Cardoso da Silva
 * @since  18-02-2011
 */
@FacesConverter (value="TelefoneConverter")
public class TelefoneConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        value = value.replace("(", "");
        value = value.replace(")", "");
    	value = value.replace("-", "");
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String numero= value.toString();
        if(numero != null && numero.length() == 10){
        	numero = "("+numero.substring(0, 2) + ")" + numero.substring(2,6) + "-" + numero.substring(5,9);
        }
       return numero;
    }

}
