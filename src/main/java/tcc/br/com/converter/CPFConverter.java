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
@FacesConverter (value="CPFConverter")
public class CPFConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return removeChar(removeChar(value, '.'),'-');
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String cpf= value.toString();
        if(cpf != null && cpf.length() == 11){
            cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
        }
       return cpf;
    }

    public static String removeChar(String s, char c) {
       String r = "";
       for (int i = 0; i < s.length(); i ++) {
          if (s.charAt(i) != c) r += s.charAt(i);
       }
       return r;
    }

}
