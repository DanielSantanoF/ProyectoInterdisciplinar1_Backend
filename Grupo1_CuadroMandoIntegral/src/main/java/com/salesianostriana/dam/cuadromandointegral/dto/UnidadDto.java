package com.salesianostriana.dam.cuadromandointegral.dto;

import java.util.List;

import com.salesianostriana.dam.cuadromandointegral.model.IndicadorUnidad;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicadorUnidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase dto para una Unidad
 * 
 * @author jldiez
 * 
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UnidadDto {

	private Long id;
	private String nombreUnidad;
	private Long curso;
	private List<ValorIndicadorUnidad> listaValoresIndicadorUnidad;
	private List<IndicadorUnidad> listaIndicadoresUnidad;

}
