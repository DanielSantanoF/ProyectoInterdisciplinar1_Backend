package com.salesianostriana.dam.cuadromandointegral.errores;

/**
 * Excepción si el usuario no se encuentra
 * 
 * @author Daniel Santano Fernández 
 *
 */
public class UserNotFoundException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4475007554095522837L;

	/**
	 * Manejo de excepcion de usuario no encontrado
	 */
	public UserNotFoundException() {
		super("El usuario no se ha encontrado");
	}

	/**
	 * Manejo de excepcion de usuario no encontrado
	 * @param username username del usuario
	 */
	public UserNotFoundException(String username) {
		super(String.format("Usuario %s no se ha encontrado", username));
	}

}
