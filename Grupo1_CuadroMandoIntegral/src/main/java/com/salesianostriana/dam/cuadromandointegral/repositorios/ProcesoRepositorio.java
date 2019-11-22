package com.salesianostriana.dam.cuadromandointegral.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.Proceso;
/**
 * Repositorio de proceso
 * @author Miguel Lázaro Domíngue
 *
 */
@Repository
public interface ProcesoRepositorio extends JpaRepository<Proceso,Long>{
	/**
	 * Busca los procesos por el id de curso academico
	 * @param id El id del Curso Académico
	 * @return Lista de Procesos
	 */
	List<Proceso> findAllByCursoAcademicoId(Long id);

}
