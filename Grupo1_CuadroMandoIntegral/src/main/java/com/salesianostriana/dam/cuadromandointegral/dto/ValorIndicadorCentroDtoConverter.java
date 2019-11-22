package com.salesianostriana.dam.cuadromandointegral.dto;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicadorCentro;

import lombok.RequiredArgsConstructor;

/**
 * dto converter de valor indicador centro
 * 
 * @author José Antonio Llamas
 *
 */
@RequiredArgsConstructor
@Component
public class ValorIndicadorCentroDtoConverter {

	/**
	 * Convierte un ValorIndicadorCentro en ValorIndicadorCentroDto
	 * @param vic ValorIndicadorCentro a convertir
	 * @return El Dto
	 */
	public ListaValoresIndicadorDto convertValorIndicadorCentroToListaValoresIndicadorDto(ValorIndicadorCentro vic) {
		return ListaValoresIndicadorDto.builder()
				.id(vic.getId())
				.cursoAcademico(vic.getCursoAcademico())
				.nombreIndicador(vic.getIndicadorCentro().getNombreIndicador())
				.valorConforme(vic.getValorConforme())
				.valorReal(vic.getValorReal())
				.evaluacion(vic.getPsm().getEvaluacion())
				.build();
	}
	
}
