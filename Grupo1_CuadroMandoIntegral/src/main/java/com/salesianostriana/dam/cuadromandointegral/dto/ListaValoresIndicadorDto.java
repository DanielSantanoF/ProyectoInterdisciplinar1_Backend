package com.salesianostriana.dam.cuadromandointegral.dto;

import com.salesianostriana.dam.cuadromandointegral.model.CursoAcademico;
import com.salesianostriana.dam.cuadromandointegral.model.enums.Evaluacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Valores de un indicador, independientemente de que sea de Centro o de Unidad
 * 
 * @author José Antonio Llamas
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaValoresIndicadorDto {

	private Long id;
	private String nombreIndicador;
	private CursoAcademico cursoAcademico;
	private Double valorReal;
	private Double valorConforme;
	private Evaluacion evaluacion;
	
}
