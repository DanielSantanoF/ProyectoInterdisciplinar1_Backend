package com.salesianostriana.dam.cuadromandointegral.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicadorUnidad;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Repository
public interface ValorIndicadorUnidadRepositorio extends JpaRepository<ValorIndicadorUnidad, Long>{

	/**
	 * todos los valores segun un psm
	 * @param psm psm a buscar
	 * @return lista de los valores encontrados
	 */
	List<ValorIndicadorUnidad> findAllByPsm(Psm psm);
	
	/**
	 * todos los valores segun el id de la unidad
	 * @param id id de la unidad
	 * @return lista de valores
	 */
	List<ValorIndicadorUnidad> findAllByIndicadorUnidadId(long id);
}
