package com.salesianostriana.dam.cuadromandointegral.dto;

import com.salesianostriana.dam.cuadromandointegral.model.enums.Evaluacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto para obtener un Psm ByCursoAcademicoIdAndEvaluacion
 * 
 * @author Daniel Santano Fernández 
 *
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ObtenerPsmDtoByCaIdAndEv {

	private Long psmId;
	private Long cursoAcademicoId;
	private Evaluacion evaluacion;

}
