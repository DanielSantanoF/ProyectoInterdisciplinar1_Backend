package com.salesianostriana.dam.cuadromandointegral.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.IndicadorUnidad;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Repository
public interface IndicadorUnidadRepositorio extends JpaRepository<IndicadorUnidad,Long>{

	/**
	 * indicador segun nombre
	 * @param name nombre del indicador
	 * @return indicador encontrado
	 */
	IndicadorUnidad findByNombreIndicador(String name);
}
