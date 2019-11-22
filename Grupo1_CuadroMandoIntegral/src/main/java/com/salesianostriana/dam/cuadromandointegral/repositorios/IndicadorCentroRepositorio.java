package com.salesianostriana.dam.cuadromandointegral.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.IndicadorCentro;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Repository
public interface IndicadorCentroRepositorio extends JpaRepository<IndicadorCentro,Long>{

	/**
	 * Indicador de centro segun el nombre
	 * @param name nombre del indicador
	 * @return indicador encontrado
	 */
	IndicadorCentro findByNombreIndicador(String name);
}
