package com.salesianostriana.dam.cuadromandointegral.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.Colegio;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Repository
public interface ColegioRepositorio extends JpaRepository<Colegio, Long>{

	/**
	 * Devuelve el primer colegio que encuentra con ese id
	 * @param id id del colegio
	 * @return el colegio encontrado
	 */
	Colegio findFirstById(long id);

}
