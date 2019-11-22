package com.salesianostriana.dam.cuadromandointegral.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.PuntoControl;

import lombok.RequiredArgsConstructor;
/**
 * Clase que gestiona la conversion del modelo Punto de control a dto
 * 
 * @author mlazaro
 *
 */
@RequiredArgsConstructor
@Component
public class PuntoControlDtoConverter {

	/**
	 * lo necesario para la clase
	 */
	private final PsmDtoConverter psmConverter;
	private final ColegioDtoConverter colegioConverter;
	
	/**
	 * Metodo que convierte un punto de control a dto
	 * @param pc Instancia de Punto de Control
	 * @return PuntoControlDto
	 */
	public PuntoControlDto convertPuntoControlToPuntoControlDto(PuntoControl pc) {
		List<Psm> lista = pc.getListaPsm();
		ColegioDto c = colegioConverter.convertColegioToColegioDto(pc.getColegio());
		//// @formatter:off
		return PuntoControlDto.builder()
				.id(pc.getId())
				.fecha(pc.getFecha())
				.colegioDto(c)
				.psm(lista.stream().map(psmConverter::convertPsmToPsmDto).collect(Collectors.toList())).build();
		// @formatter:on

	
	}
	/**
	 * Método que convierte un punto de control vacío a dto
	 * @param pc Instancia de PuntoControl
	 * @return un puntoControlDto
	 */
	public PuntoControlDto convertPuntoControlVacioToPuntoControlDto(PuntoControl pc) {
		ColegioDto c = colegioConverter.convertColegioToColegioDto(pc.getColegio());
		//// @formatter:off
		return PuntoControlDto.builder()
				.id(pc.getId())
				.fecha(pc.getFecha())
				.colegioDto(c)
				.build();
		// @formatter:on
	}
	
}
