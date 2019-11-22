package com.salesianostriana.dam.cuadromandointegral.errores;

/**
 * Excepcion al registrar un usuario nuevo
 * 
 * @author Daniel Santano Fernández 
 *
 */

public class RegisterNewUserException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -247355172899421626L;

	/**
	 * Manejamos el error al crear un usuario
	 * @param mensaje mensaje de error
	 */
	public RegisterNewUserException(String mensaje) {
		super(mensaje);
	}

}
