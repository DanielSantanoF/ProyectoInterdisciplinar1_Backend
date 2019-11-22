package com.salesianostriana.dam.cuadromandointegral.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase dto del modelo Indicador
 * 
 * @author jldiez
 * 
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class IndicadorDto {
	
	private Long id;
	private ProcesoDto proceso;
	private String nombreIndicador;
	private boolean esPorcentaje;
	
}


