package com.salesianostriana.dam.cuadromandointegral.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Clase dto del modelo Proceso
 * 
 * @author mlazaro
 *
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProcesoDto {

	private Long id;
	private String nombre;
	private String tipo;
	private CursoAcademicoDto cursoAcademico;
	
}
