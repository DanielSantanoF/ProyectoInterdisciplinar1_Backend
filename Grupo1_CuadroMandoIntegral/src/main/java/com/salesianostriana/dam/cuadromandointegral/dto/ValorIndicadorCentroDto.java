package com.salesianostriana.dam.cuadromandointegral.dto;

import com.salesianostriana.dam.cuadromandointegral.model.CursoAcademico;
import com.salesianostriana.dam.cuadromandointegral.model.enums.Evaluacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto de Valor Indicador Centro
 * 
 * @author José Antonio Llamas
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValorIndicadorCentroDto {

	private Long id;
	private String nombreIndicador;
	private CursoAcademico cursoAcademico;
	private Double valorReal;
	private Double valorConforme;
	private Evaluacion evaluacion;

}
