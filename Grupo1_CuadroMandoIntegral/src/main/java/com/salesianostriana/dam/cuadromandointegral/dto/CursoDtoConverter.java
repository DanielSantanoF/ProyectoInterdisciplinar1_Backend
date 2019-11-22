package com.salesianostriana.dam.cuadromandointegral.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.Curso;
import com.salesianostriana.dam.cuadromandointegral.model.Unidad;
import com.salesianostriana.dam.cuadromandointegral.servicios.EtapaServicio;

/**
 * Clase que gestiona la conversion del modelo curso a dto
 * @author Miguel Lázaro Dominguez
 *
 */
@Component
public class CursoDtoConverter {
	
	@Autowired
	private UnidadDtoConverter unidadConverter;
	@Autowired
	private EtapaServicio etapaServicio;
	
	/**
	 * Transforma un curso en un dto
	 * @param curso Instancia de Curso
	 * @return Un CursoDto
	 */
	public CursoDto convertCursoToCursoDto(Curso curso) {
		List<Unidad> u = new ArrayList<>();
		u = curso.getUnidades();
		return CursoDto.builder()
				.id(curso.getId())
				.nombreEtapa(curso.getEtapa().getNombreSubEtapa())
				.nombreCurso(curso.getNombreCurso())
				.peso(curso.getPeso())
				.unidades(u.stream().map(unidadConverter:: convertUnidadToUnidadDto)
						.collect(Collectors.toList())).build();		
	}
	
	/**
	 * Transforma dto en un curso
	 * @param cursoDto Instancia de CursoDto
	 * @return Un Curso
	 */
	public Curso cursoDtoToCurso(CursoDto cursoDto) {
		return Curso.builder()
				.nombreCurso(cursoDto.getNombreCurso())
				.etapa(etapaServicio.findByNombreSubEtapa(cursoDto.getNombreEtapa()))
				.unidades(new ArrayList<Unidad>())
				.build();
	}
	
}
