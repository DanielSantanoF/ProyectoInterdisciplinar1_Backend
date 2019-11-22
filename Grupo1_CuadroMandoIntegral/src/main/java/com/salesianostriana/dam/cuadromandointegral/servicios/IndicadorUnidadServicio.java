package com.salesianostriana.dam.cuadromandointegral.servicios;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.model.IndicadorUnidad;
import com.salesianostriana.dam.cuadromandointegral.repositorios.IndicadorUnidadRepositorio;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Service
public class IndicadorUnidadServicio extends ServicioBase<IndicadorUnidad,Long,IndicadorUnidadRepositorio>{

	/**
	 * llama al metodo del repo
	 * @param name nombre del indicadorunidad
	 * @return inndicadorunidad encontrado
	 */
	public IndicadorUnidad findByName(String name) {
		return repositorio.findByNombreIndicador(name);
	}
}
