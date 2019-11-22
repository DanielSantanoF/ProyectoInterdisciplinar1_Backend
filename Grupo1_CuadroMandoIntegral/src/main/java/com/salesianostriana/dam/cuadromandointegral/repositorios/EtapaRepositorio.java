package com.salesianostriana.dam.cuadromandointegral.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.Etapa;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Repository
public interface EtapaRepositorio extends JpaRepository<Etapa, Long> {

	/**
	 * Encuentra una etapa segun su id
	 * @param id id de la etapa
	 * @return etapa encontrada dentro de una list
	 */
	List<Etapa> findByCursoAcademicoId(Long id);

	/**
	 * Encuentra una etapa segun el nombre de subetapa
	 * @param nombreEtapa nombre de la subetapa
	 * @return etapa encontrada
	 */
	Etapa findByNombreSubEtapa(String nombreEtapa);

	/**
	 * Etapa por colegio id
	 * @param id id del colegio
	 * @return lista con la etapa
	 */
	List<Etapa> findByColegioId(Long id);

	/**
	 * Todas las etapas segun el curso academico id
	 * @param id id del curso academico
	 * @return lista con las etapas
	 */
	List<Etapa> findAllByCursoAcademicoId(Long id);


}
