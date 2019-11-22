package com.salesianostriana.dam.cuadromandointegral.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.model.Etapa;
import com.salesianostriana.dam.cuadromandointegral.repositorios.EtapaRepositorio;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Service
public class EtapaServicio extends ServicioBase<Etapa,Long,EtapaRepositorio>{


	/**
	 * llama al metodo del repo
	 * @param id id de curso academico
	 * @return lista con la etapa
	 */
	public List<Etapa> findByCursoAcademicoId(Long id) {
		return repositorio.findByCursoAcademicoId(id);
	}

	/**
	 * llama al metodo del repo
	 * @param nombreEtapa nombre de la etapa
	 * @return etapa encontrada
	 */
	public Etapa findByNombreSubEtapa(String nombreEtapa) {
		return repositorio.findByNombreSubEtapa(nombreEtapa);
	}

	/**
	 * llama al metodo del repo
	 * @param id id del colegio
	 * @return lista con la etapa encontrada
	 */
	public List<Etapa> findByColegioId(Long id) {
		return repositorio.findByColegioId(id);
	}

	/**
	 * llama al metodo del reo
	 * @param id de curso academico
	 * @return lista con la etapa encontrada
	 */
	public List<Etapa> findAllByCursoAcademicoId(Long id) {
		return repositorio.findAllByCursoAcademicoId(id);
	}

}
