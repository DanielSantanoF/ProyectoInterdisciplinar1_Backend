package com.salesianostriana.dam.cuadromandointegral.dto;

import java.util.List;

import com.salesianostriana.dam.cuadromandointegral.model.Proceso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase dto del modelo IndicadorCentro
 * 
 * @author jldiez
 * 
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class IndicadorCentroDto {
	
	private Long id;
	private Proceso proceso;
	private String nombreIndicador;
	private boolean esPorcentaje;
	private List<ColegioDto> colegioDto;
	
}
