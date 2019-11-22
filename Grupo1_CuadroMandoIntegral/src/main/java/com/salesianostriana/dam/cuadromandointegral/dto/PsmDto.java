package com.salesianostriana.dam.cuadromandointegral.dto;

import java.util.List;

import com.salesianostriana.dam.cuadromandointegral.model.enums.Evaluacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto de Psm
 * 
 * @author Daniel Santano Fernández
 *
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PsmDto {
	
	private Long id;
	private String nombrePsm;
	private Evaluacion evaluacion;
	private Integer peso;
	private ColegioDto colegioDto;
	private CursoAcademicoDto cursoAcademico;
	private List<ValorIndicadorDto> listaValoresIndicadores;

}
