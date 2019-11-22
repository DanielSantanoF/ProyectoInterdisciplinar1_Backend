package com.salesianostriana.dam.cuadromandointegral.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto para colegio
 * 
 * @author Daniel Santano Fernández 
 *
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ColegioDto {

	private Long id;
	private String nombre;
	
}
