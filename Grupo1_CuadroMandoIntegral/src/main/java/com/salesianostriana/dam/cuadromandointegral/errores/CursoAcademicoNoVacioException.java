package com.salesianostriana.dam.cuadromandointegral.errores;

/**
 * Excepción si un Curso Académico no se puede borrar por contener algún Psm
 * 
 * @author José Antonio Llamas
 *
 */
public class CursoAcademicoNoVacioException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7233195296150965906L;

	public CursoAcademicoNoVacioException(String nombreCursoAcad) {
		super("El Curso Académico " + nombreCursoAcad +" tiene datos relacionados y no se puede eliminar.");
	}
}