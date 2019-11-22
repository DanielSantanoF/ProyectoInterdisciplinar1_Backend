package com.salesianostriana.dam.cuadromandointegral.dto;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.Colegio;

import lombok.RequiredArgsConstructor;

/**
 * El DtoConverter de Colegio
 * 
 * @author Daniel Santano Fernández 
 *
 */

@RequiredArgsConstructor
@Component
public class ColegioDtoConverter {
	
	/**
	 * Metodo para transformar un colegio a colegioDto
	 * @param c el colegio a convertir
	 * @return dto el colegio convertido a dto
	 */
	public ColegioDto convertColegioToColegioDto(Colegio c) {
		return ColegioDto.builder()
				.id(c.getId())
				.nombre(c.getNombreColegio())
				.build();
	}
	
}
