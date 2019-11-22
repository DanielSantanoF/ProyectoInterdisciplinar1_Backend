package com.salesianostriana.dam.cuadromandointegral.dto;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.Curso;
import com.salesianostriana.dam.cuadromandointegral.model.Etapa;
import com.salesianostriana.dam.cuadromandointegral.servicios.ColegioServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.CursoAcademicoServicio;

import lombok.RequiredArgsConstructor;

/**
 * Clase para convertir DtoEtapas a Etapas y viceversa
 * 
 * @author jldiez
 * 
 */

@Component
@RequiredArgsConstructor
public class EtapaDtoConverter {
	
	/**
	 * lo necesario para la clase
	 */
	private final ColegioDtoConverter colegioDtoConverter;
	private final ColegioServicio colegioServicio;
	private final CursoAcademicoServicio cursoacademicoServico;
	
	/**
	 * Convert de Etapa a EtapaDto
	 * @param etapa que vamos a convertir
	 * @return dto de la etapa que le pasamos
	 */
	public EtapaDto convertEtapaToEtapaDto(Etapa etapa) {
		return EtapaDto.builder()
				.id(etapa.getId())
				.nombreSubEtapa(etapa.getNombreSubEtapa())
				.colegio(colegioDtoConverter.convertColegioToColegioDto(etapa.getColegio()))
				.build();
	}
	
	/**
	 * Convert de EtapaDto a Etapa
	 * @param etapaDto Dto de una nueva etapa
	 * @return Etapa creada con los datos del dto
	 */
	public Etapa convertEtapaDtoToEtapa(NuevaEtapaDto etapaDto) {
		return Etapa.builder()
				.nombreSubEtapa(etapaDto.getNombreSubEtapa())
				.etapa(null)
				.colegio(colegioServicio.findById(etapaDto.getColegio().getId()))
				.cursoAcademico(cursoacademicoServico.findById(etapaDto.getCursoAcademico()))
				.listaCursos(new ArrayList<Curso>())
				.build();
	}
	
}
