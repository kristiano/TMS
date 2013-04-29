package br.com.sigd.util;

import br.com.sigd.model.Pessoa;
import br.com.sigd.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author marceloclaudios
 */
public abstract class ApplicationUtilies {

    public static EntityManager catchEntityManager() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        EntityManager entityManager = (EntityManager) FacesContext.getCurrentInstance().
                getApplication().getELResolver().getValue(elContext, null, "entityManager");
        return entityManager;
    }

    public static Object getAuthenticadedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>(authentication.getAuthorities());
        //String role = roles.get(0).getAuthority();
        String username = authentication.getName();
        UsuarioRepository aRepository = new UsuarioRepository(catchEntityManager());
        return aRepository.loadLoggedUser(username);
    }

    public static boolean isLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null || authentication.isAuthenticated()) {
            return true;
        } else {
            return false;
        }

    }

    public static String loggedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public static String trocaAcentuacao(String acentuada) {
        char[] acentuados = new char[]{'ç', 'á', 'à', 'ã', 'â', 'ä', 'é', 'è', 'ê', 'ë', 'í', 'ì', 'î', 'ï', 'ó', 'ò', 'õ', 'ô', 'ö', 'ú', 'ù', 'û', 'ü'};

        char[] naoAcentuados = new char[]{'c', 'a', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'e', 'i', 'i', 'i', 'i', 'o', 'o', 'o', 'o', 'o', 'u', 'u', 'u', 'u'};

        for (int i = 0; i < acentuados.length; i++) {
            acentuada = acentuada.replace(acentuados[i], naoAcentuados[i]);
            acentuada = acentuada.replace(Character.toUpperCase(acentuados[i]), Character.toUpperCase(naoAcentuados[i]));
        }

        acentuada = acentuada.toLowerCase().replaceAll(" ", "-");

        return acentuada;
    }

    public static Pessoa getUsuarioLogado() {
        Authentication anAuthentication = SecurityContextHolder.getContext().getAuthentication();
        Pessoa anUser = null;
        if (anAuthentication.getPrincipal() instanceof Pessoa) {
            anUser = (Pessoa) anAuthentication.getPrincipal();
        }
        return anUser;
    }
}
