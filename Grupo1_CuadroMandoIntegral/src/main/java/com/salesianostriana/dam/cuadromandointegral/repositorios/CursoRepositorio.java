package com.salesianostriana.dam.cuadromandointegral.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.Curso;
/**
 * Repositorio de curso
 * @author mlazaro
 *
 */
@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long>{
	/**
	 * Busca los cursos por el id de etapa
	 * @param id El id de la etapa
	 * @return Lista de Cursos
	 */
	List<Curso> findAllByEtapaId(Long id);

}
