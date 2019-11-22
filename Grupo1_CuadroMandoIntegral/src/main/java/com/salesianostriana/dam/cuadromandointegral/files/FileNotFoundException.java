package com.salesianostriana.dam.cuadromandointegral.files;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepcion de archivo no encontrado
 * 
 * @author Daniel Santano Fernández 
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {

	/**
	 * Excepcion que salta si no se encuentra el archivo
	 */
	private static final long serialVersionUID = 3108688991656698690L;

	/**
	 * Excepcion de no se encuentra el file
	 * @param message mensaje de error
	 */
	public FileNotFoundException(String message) {
        super(message);
    }

	/**
	 * Excepcion de no se encuentra el file
	 * @param message mensaje de error
	 * @param cause la causa del error
	 */
	public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
