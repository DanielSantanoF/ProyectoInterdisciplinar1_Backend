package com.salesianostriana.dam.cuadromandointegral.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase dto del modelo Punto de control
 * 
 * @author mlazaro
 *
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PuntoControlDto {
	
	private Long id;
	 @JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fecha;
	private List<PsmDto> psm; 
	private ColegioDto colegioDto;
	
}
