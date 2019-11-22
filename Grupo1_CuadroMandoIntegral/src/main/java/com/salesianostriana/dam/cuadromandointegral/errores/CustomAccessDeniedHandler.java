package com.salesianostriana.dam.cuadromandointegral.errores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Manejamos el acceso denegado
 * 
 * @author Daniel Santano Fernández 
 *
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	/**
	 * Metodo para manejar el acceso denegado
	 */
	@Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) {
        throw e;
    }
}
