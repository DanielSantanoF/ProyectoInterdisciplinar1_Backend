package com.salesianostriana.dam.cuadromandointegral.dto;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.Proceso;

import lombok.RequiredArgsConstructor;

/**
 * Clase que gestiona la conversión del modelo Proceso a dto
 * 
 * @author mlazaro
 *
 */
@RequiredArgsConstructor
@Component
public class ProcesoDtoConverter {

	/**
	 * lo necesario para la clase
	 */
	private final CursoAcademicoDtoConverter cursoAcademicoConverter;
	
	/**
	 * Metodo que transforma un proceso a dto
	 * @param p instancia de Proceso
	 * @return Un ProcesoDto
	 */
	public ProcesoDto convertProcesoToProcesoDto(Proceso p) {
		return ProcesoDto.builder()
				.id(p.getId())
				.nombre(p.getNombreProceso())
				.tipo(p.getTipo())
				.cursoAcademico(cursoAcademicoConverter.convertCursoAcademicoToCursoAcademicoDto(p.getCursoAcademico()))
				.build();
	}
}
