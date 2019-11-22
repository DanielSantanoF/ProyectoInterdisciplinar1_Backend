package com.salesianostriana.dam.cuadromandointegral.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.model.Colegio;
import com.salesianostriana.dam.cuadromandointegral.repositorios.ColegioRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de colegios
 * 
 * @author Daniel Santano Fernández
 *
 */
@Service
@RequiredArgsConstructor
public class ColegioServicio extends ServicioBase<Colegio, Long, ColegioRepositorio> {

	/**
	 * llama al metodo del repo
	 * @param id id del colegio
	 * @return colegio encontrado
	 */
	public Colegio findFirstById(Long id) {
		return repositorio.findFirstById(id);
	}
	
	/**
	 * llama a la peticion del repo
	 * @param ids lista de ids de loc colegios
	 * @return lista de colegios
	 */
	public List<Colegio> findColegiosByIds(List<Long> ids) {
		return repositorio.findAllById(ids);
	}
	
	
}
