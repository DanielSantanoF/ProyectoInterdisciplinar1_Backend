package com.salesianostriana.dam.cuadromandointegral.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase dto para una nueva Unidad
 * 
 * @author jldiez
 * 
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class NuevaUnidadDto {
	
	private String nombreUnidad;
	private Long curso;
	
}
