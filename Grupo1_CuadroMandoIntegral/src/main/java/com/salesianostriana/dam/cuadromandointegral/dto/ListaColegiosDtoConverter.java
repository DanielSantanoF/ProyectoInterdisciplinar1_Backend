package com.salesianostriana.dam.cuadromandointegral.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.Colegio;
import com.salesianostriana.dam.cuadromandointegral.repositorios.ColegioRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * ElDtoConverter de ListaColegio
 * 
 * @author Daniel Santano Fernández 
 *
 */

@RequiredArgsConstructor
@Component
public class ListaColegiosDtoConverter {

	/**
	 * Lo necesario para la clase
	 */
	private final ColegioRepositorio colegioRepositorio;
	private final ColegioDtoConverter colegioDtoConverter;
	
	/**
	 * Metodo para obtener todos los colegios y transformarlos a dto
	 * @return Lista de ColegioDto con dto de todos los colegios
	 */
	public List<ColegioDto> convertListaColegioToListaColegioDto() {
	List<Colegio> lista = colegioRepositorio.findAll();
	return lista.stream().map(colegioDtoConverter :: convertColegioToColegioDto)
			.collect(Collectors.toList());
	}
}
