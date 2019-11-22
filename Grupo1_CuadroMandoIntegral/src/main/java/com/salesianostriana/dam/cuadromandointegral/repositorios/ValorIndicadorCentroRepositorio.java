package com.salesianostriana.dam.cuadromandointegral.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicadorCentro;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Repository
public interface ValorIndicadorCentroRepositorio extends JpaRepository<ValorIndicadorCentro, Long>{

	/**
	 * todos los valores segun un psm
	 * @param psm psm que se usa para buscar
	 * @return lista de valores encontrados
	 */
	List<ValorIndicadorCentro> findAllByPsm(Psm psm);
	
	/**
	 * todos los valores seun el id del indicador centro
	 * @param id id del centro
	 * @return lista de valores indicadores
	 */
	List<ValorIndicadorCentro> findAllByIndicadorCentroId(long id);
	
}
