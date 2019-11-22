package com.salesianostriana.dam.cuadromandointegral.dto;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicador;

import lombok.RequiredArgsConstructor;

/**
 * DtoConverter para ValorIndicador
 * 
 * @author Daniel Santano Fernández 
 *
 */

@RequiredArgsConstructor
@Component
public class ValorIndicadorDtoConverter {

	/**
	 * Metodo para pasar de valorIndicador a dto
	 * @param v el valorIndicador a transformar
	 * @return el dto 
	 */
	public ValorIndicadorDto convertValorIndicadorToValorIndicadorDto(ValorIndicador v) {
		return ValorIndicadorDto.builder()
				.id(v.getId())
				.valorReal(v.getValorReal())
				.valorConforme(v.getValorConforme())
				.build();
	}
	
}
