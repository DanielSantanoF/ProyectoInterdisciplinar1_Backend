package com.salesianostriana.dam.cuadromandointegral.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.model.Unidad;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UnidadRepositorio;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Service
public class UnidadServicio extends ServicioBase<Unidad,Long,UnidadRepositorio>{

	/**
	 * llama al metodo del repo
	 * @param name nombre de la unidad
	 * @return unidad encontrada
	 */
	public Unidad findByName(String name) {
		return repositorio.findByNombreUnidad(name);
	}

	/**
	 * llama al metodo del repo
	 * @param id id del curso
	 * @return unidad encontrada
	 */
	public List<Unidad> findAllByCursoId(Long id) {
		return repositorio.findAllByCursoId(id);
	}
}
