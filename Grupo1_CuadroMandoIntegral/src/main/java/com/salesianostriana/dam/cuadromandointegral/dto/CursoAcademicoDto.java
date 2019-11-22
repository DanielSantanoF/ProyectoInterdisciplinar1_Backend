package com.salesianostriana.dam.cuadromandointegral.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto para curso academico
 * 
 * @author Daniel Santano Fernández 
 *
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CursoAcademicoDto {

	private Long id;
	private String nombreCursoAcademico;
}
