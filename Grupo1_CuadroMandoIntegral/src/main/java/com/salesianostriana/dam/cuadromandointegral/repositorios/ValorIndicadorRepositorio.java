package com.salesianostriana.dam.cuadromandointegral.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicador;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Repository
public interface ValorIndicadorRepositorio extends JpaRepository<ValorIndicador, Long>{

	/**
	 * losta de valores segun el psm id
	 * @param id id del psm
	 * @return lista de valores encontrados
	 */
	List<ValorIndicador> findAllByPsmId(long id);
	
}
