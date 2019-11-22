package com.salesianostriana.dam.cuadromandointegral.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.enums.Evaluacion;
/**
 * Repositorio de psm
 * 
 * @author Daniel Santano Fernández
 *
 */
@Repository
public interface PsmRepositorio extends JpaRepository<Psm, Long>{
	
	/**
	 * Psm segun su id 
	 * @param id del psm
	 * @return psm encontrado
	 */
	Psm findFirstById(long id);

	/**
	 * psm segun el curso academico id 
	 * @param id del curso academico
	 * @return lista de los psm
	 */
	List<Psm> findAllByCursoAcademicoId(long id);

	/**
	 * Psm encontrado segun el id el curso academico y la evaluacion
	 * @param id del psm
	 * @param cursoAcademicoId id del curso academico
	 * @param evaluacion evaluacion del psm
	 * @return psm encontrado
	 */
	Psm findFirstByIdAndCursoAcademicoIdAndEvaluacion(long id, long cursoAcademicoId, Evaluacion evaluacion);
	
}
