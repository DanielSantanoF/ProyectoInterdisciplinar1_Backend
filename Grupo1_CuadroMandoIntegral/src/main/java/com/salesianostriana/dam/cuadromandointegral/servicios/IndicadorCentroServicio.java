package com.salesianostriana.dam.cuadromandointegral.servicios;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.model.IndicadorCentro;
import com.salesianostriana.dam.cuadromandointegral.repositorios.IndicadorCentroRepositorio;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Service
public class IndicadorCentroServicio extends ServicioBase<IndicadorCentro, Long, IndicadorCentroRepositorio> {

	/**
	 * llama al metodo del repo
	 * @param name nombre del indicador
	 * @return indicador encontrado
	 */
	public IndicadorCentro findByName(String name) {
		return repositorio.findByNombreIndicador(name);
	}

	/**
	 * llama al metodo del repo
	 * @param id id del indicador
	 * @return boolean segun lo encuentre o no
	 */
	public boolean existsById(Long id) {
		return repositorio.existsById(id);
	}
}
