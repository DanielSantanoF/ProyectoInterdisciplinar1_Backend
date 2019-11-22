package com.salesianostriana.dam.cuadromandointegral.servicios;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.model.CursoAcademico;
import com.salesianostriana.dam.cuadromandointegral.repositorios.CursoAcademicoRepositorio;

import lombok.RequiredArgsConstructor;
/**
 * Servicios de los Cursos Académicos
 * @author José Antonio Llamas
 *
 */
@Service
@RequiredArgsConstructor
public class CursoAcademicoServicio extends ServicioBase<CursoAcademico, Long, CursoAcademicoRepositorio> {

	/**
	 * Busca un Curso académico por su id
	 * @param id La id del Curso académico a buscar
	 * @return Una instancia de Curso académico
	 */
	public CursoAcademico findFirstById(long id) {
		return repositorio.findFirstById(id);
	}
	
	/**
	 * Busca un Curso académico por su nombre
	 * @param name el nombre del Curso académico a buscar
	 * @return Una instancia de Curso académico
	 */
	public CursoAcademico findFirstByNombreCursoAcademico(String name) {
		return repositorio.findFirstByNombreCursoAcademico(name);
		
	}
	
	/**
	 * Averigua si un Curso académico existe
	 * @param id La id del Curso académico
	 * @return Booleano que indica si existe o no
	 */
	public boolean existsById(Long id) {
		return repositorio.existsById(id);
	}
}
