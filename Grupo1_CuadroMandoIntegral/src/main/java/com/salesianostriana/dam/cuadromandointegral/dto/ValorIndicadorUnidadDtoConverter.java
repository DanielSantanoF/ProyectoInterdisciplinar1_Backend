package com.salesianostriana.dam.cuadromandointegral.dto;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicadorUnidad;

import lombok.RequiredArgsConstructor;

/**
 * dto converter de valor indicador unidad
 * 
 * @author José Antonio Llamas
 *
 */
@RequiredArgsConstructor
@Component
public class ValorIndicadorUnidadDtoConverter {

	/**
	 * Convierte un ValorIndicadorUnidad en ValorIndicadorUnidadDto
	 * @param viu ValorIndicadorUnidad a convertir
	 * @return El Dto
	 */
	public ListaValoresIndicadorDto convertValorIndicadorUnidadToListaValoresIndicadorDto(ValorIndicadorUnidad viu) {
		return ListaValoresIndicadorDto.builder()
				.id(viu.getId())
				.cursoAcademico(viu.getCursoAcademico())
				.nombreIndicador(viu.getIndicadorUnidad().getNombreIndicador())
				.valorConforme(viu.getValorConforme())
				.valorReal(viu.getValorReal())
				.evaluacion(viu.getPsm().getEvaluacion())
				.build();
	}
	
}
