package com.salesianostriana.dam.cuadromandointegral.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.CursoAcademico;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Repository
public interface CursoAcademicoRepositorio extends JpaRepository<CursoAcademico, Long> {

	/**
	 * Encuentra un Curso académico por Id
	 * @param id La id del Curso académico
	 * @return Instancia de Curso académico
	 */
	CursoAcademico findFirstById(long id);

	/**
	 * Encuentra un Curso académico por su nombre
	 * @param name El nombre del Curso académico
	 * @return Instancia de Curso académico
	 */
	CursoAcademico findFirstByNombreCursoAcademico(String name);
}
