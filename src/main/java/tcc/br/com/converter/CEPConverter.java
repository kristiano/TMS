package tcc.br.com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Leslie Cardoso da Silva
 * @since  03-11-2010
 */
@FacesConverter (value="CEPConverter")
public class CEPConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	value = value.replace(".", "");
    	value = value.replace("-", "");
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String cep= value.toString();
        if(cep != null && cep.length() == 8){
        	cep = cep.substring(0, 2) + "." + cep.substring(2,5) + "-" + cep.substring(5,8);
        }
       return cep;
    }

}
