package com.salesianostriana.dam.cuadromandointegral.dto;


import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.IndicadorUnidad;
import com.salesianostriana.dam.cuadromandointegral.model.Unidad;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicadorUnidad;
import com.salesianostriana.dam.cuadromandointegral.servicios.CursoServicio;

import lombok.RequiredArgsConstructor;

/**
 * Clase para convertir DtoUnidad a Unidad y viceversa
 * 
 * @author jldiez
 * 
 */

@RequiredArgsConstructor
@Component
public class UnidadDtoConverter {
	
	/**
	 * lo necesario para la clase
	 */
	private CursoServicio cursoServicio;
	
	/**
	 * Metodo para convertir una Unidad a UnidadDto
	 * @param u unidad que le pasamos para convertir
	 * @return dto de la unidad
	 */
	public UnidadDto convertUnidadToUnidadDto(Unidad u) {
		return UnidadDto.builder()
				.id(u.getId())
				.nombreUnidad(u.getNombreUnidad())
				.curso(u.getCurso().getId())
				.listaIndicadoresUnidad(u.getListaIndicadoresUnidad())
				.listaValoresIndicadorUnidad(u.getListaValoresIndicadorUnidad())
				.build();
	}
	
	/**
	 * Metodo para convertir una UnidadDto a Unidad
	 * @param unidadDto UnidadDto que le pasamos para convertir
	 * @return unidad creada a partir del dto
	 */
	public Unidad convertUnidadDtoToUnidad(NuevaUnidadDto unidadDto) {
		return Unidad.builder()
				.nombreUnidad(unidadDto.getNombreUnidad())
				.curso(cursoServicio.findById(unidadDto.getCurso()))
				.listaIndicadoresUnidad(new ArrayList<IndicadorUnidad>())
				.listaValoresIndicadorUnidad(new ArrayList<ValorIndicadorUnidad>())
				.build();
	}
	
}
