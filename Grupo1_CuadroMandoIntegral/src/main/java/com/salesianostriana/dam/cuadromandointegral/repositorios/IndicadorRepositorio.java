package com.salesianostriana.dam.cuadromandointegral.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.Indicador;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Repository
public interface IndicadorRepositorio extends JpaRepository<Indicador, Long>{

	/**
	 * Indicador segun su nombre
	 * @param name nombre del indicador
	 * @return el indicador encontrado
	 */
	List<Indicador> findByNombreIndicador(String name);

	/**
	 * indicador segin el id de proceso
	 * @param id del proceso
	 * @return indicador en una lista
	 */
	List<Indicador> findByProcesoId(Long id);
	
	/**
	 * indicador por id 
	 * @param id id del indicador
	 * @return indicador encontrado
	 */
	Indicador findFirstById(long id);
	
}
