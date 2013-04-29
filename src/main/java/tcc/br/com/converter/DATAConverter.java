/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcc.br.com.converter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author adm
 */
@FacesConverter (value="DATAConverter")
public class DATAConverter  implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try
        {
            String data = new String();
            data = value.substring(6,10)+"-"+value.substring(3,5)+"-"+value.substring(0,2);
            return Date.valueOf(data);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        /*String v = value.toString();
        if(!v.equals(""))
            v = v.substring(8,10)+"/"+v.substring(5,7)+"-"+v.substring(0,4);
        return v;*/
        DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        return dt.format(value);
    }
    
    public static String removeChar(String s, char c) {
       String r = "";
       for (int i = 0; i < s.length(); i ++) {
          if (s.charAt(i) != c) r += s.charAt(i);
       }
       return r;
    }

}
