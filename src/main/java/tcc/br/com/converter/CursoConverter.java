package tcc.br.com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Leslie Cardoso da Silva
 */
public class CursoConverter  implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
             //seu método de pesquisa para trazer uma instância de Curso
            // return Curso.findById(new Long(value));
         }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        /
     if (value instanceof Curso) {
             Curso curso = (Curso) value;
             return curso.getId().toString();
         }

    }*/

}
