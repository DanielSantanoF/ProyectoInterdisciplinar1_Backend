package com.salesianostriana.dam.cuadromandointegral.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.Unidad;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Repository
public interface UnidadRepositorio extends JpaRepository<Unidad, Long>{

	/**
	 * unidad segun nombre
	 * @param name nombre de la unidad
	 * @return unidad encontrada
	 */
	Unidad findByNombreUnidad(String name);

	/**
	 * todas las unidaddes de un cirso
	 * @param id id del curso
	 * @return lista de unidades encontradas
	 */
	List<Unidad> findAllByCursoId(Long id);
}
