package com.salesianostriana.dam.cuadromandointegral.errores;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * Clase GlobalControllerAdvice
 * 
 * @author Daniel Santano Fernández 
 *
 */
@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{

	/**
	 * Manejo de excepciones internas
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, ex);
		return ResponseEntity.status(status).body(apiError);
	}
	
	/**
	 * Manejo de no encontrado
	 * @param ex excepcion
	 * @return responseEntity con el status y el error
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleNotFoundExceptions(Exception ex) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ApiError apiError = new ApiError(status, "No encontrado", ex);
		return ResponseEntity.status(status).body(apiError);
	}
	
}
