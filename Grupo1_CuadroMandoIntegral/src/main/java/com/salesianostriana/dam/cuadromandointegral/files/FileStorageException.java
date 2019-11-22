package com.salesianostriana.dam.cuadromandointegral.files;

/**
 * Excepcion para el FileStorage
 * 
 * @author Daniel Santano Fernández 
 *
 */

public class FileStorageException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6891297273143961157L;

	/**
	 * Manejo de excepcion al almacenar el fichero
	 * @param message mensaje de error
	 */
	public FileStorageException(String message) {
		super(message);
	}

	/**
	 * Manejo de excepcion al almacenar el fichero
	 * @param message mansaje de error
	 * @param cause la causa del error
	 */
	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
