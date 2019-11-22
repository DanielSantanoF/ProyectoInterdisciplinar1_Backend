package com.salesianostriana.dam.cuadromandointegral.errores;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Api Error
 * 
 * @author Daniel Santano Fernández 
 *
 */

@Data
@AllArgsConstructor
public class ApiError {

	/**
	 * Lo necesario para la clase
	 */
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String debugMessage;

	/**
	 * la fecha de cuando se da el error
	 */
	private ApiError() {
		timestamp = LocalDateTime.now();
	}

	/**
	 * El httpStatus del error
	 * @param status http del error
	 */
	public ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	/**
	 * el http del error junto a la excepcion
	 * @param status http del error
	 * @param ex excepción del error
	 */
	public ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	/**
	 * el http del error junto a la excepcion y un mensaje
	 * @param status http del error
	 * @param message mensaje del error
	 * @param ex excepcion del error
	 */
	public ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}

}
