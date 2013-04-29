/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.util;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 *
 * @author marceloclaudios
 */
public class SucessAuthenticationHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        ArrayList<GrantedAuthority> aRole = new ArrayList(authentication.getAuthorities());
        String role = aRole.get(0).getAuthority();
        if (role.equals("ROLE_ADMINISTRADOR")) {
            setDefaultTargetUrl("/area-administrador");
        } else if (role.equals("ROLE_COORDENADOR")) {
            setDefaultTargetUrl("/area-coordenador");
        } else if (role.equals("ROLE_PROFESSOR")) {
            setDefaultTargetUrl("/area-professor");
        }
        else { //ROLE_ACADEMICO
            setDefaultTargetUrl("/area-academico");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
