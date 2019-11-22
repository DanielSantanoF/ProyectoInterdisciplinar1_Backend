package com.salesianostriana.dam.cuadromandointegral.dto;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase dto para una nuevo Indicador
 * 
 * @author jldiez
 * 
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class NuevoIndicadorDto {
	
	private Long id;
	private Long procesos;
	private String nombreIndicador;
	private boolean esPorcentaje;
	private String tipoIndicador;
	private List<Long> colegios;
	private boolean unidadesTodas;
	
}
