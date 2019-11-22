package com.salesianostriana.dam.cuadromandointegral.dto;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.CursoAcademico;
import com.salesianostriana.dam.cuadromandointegral.model.Etapa;
import com.salesianostriana.dam.cuadromandointegral.model.Proceso;
import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicador;

/**
 * Dto converter para curso academico
 * 
 * @author Daniel Santano Fernández 
 *
 */

@Component
public class CursoAcademicoDtoConverter {

	/**
	 * Metodo para pasar de curso academico dto a entidad
	 * @param cursoAcademicoDto dto de curso academico
	 * @return entidad de curso academico
	 */
	public CursoAcademico cursoAcademicoDtoToCursoAcademico(CursoAcademicoDto cursoAcademicoDto) {
		return CursoAcademico.builder()
				.nombreCursoAcademico(cursoAcademicoDto.getNombreCursoAcademico())
				.listaProceso(new ArrayList<Proceso>())
				.listaPsm(new ArrayList<Psm>())
				.listaEtapa(new ArrayList<Etapa>())
				.listaValorIndicador(new ArrayList<ValorIndicador>())
				.build();
	}
	
	/**
	 * metodo para pasar de entidad a dto
	 * @param cursoAcademico entidad
	 * @return dto
	 */
	public CursoAcademicoDto convertCursoAcademicoToCursoAcademicoDto(CursoAcademico cursoAcademico) {
		return CursoAcademicoDto.builder()
				.id(cursoAcademico.getId())
				.nombreCursoAcademico(cursoAcademico.getNombreCursoAcademico())
				.build();
		
	}

}