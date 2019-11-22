package com.salesianostriana.dam.cuadromandointegral.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.PuntoControl;
/**
 * Repositorio de punto de control
 * @author mlazaro
 *
 */
@Repository
public interface PuntoControlRepositorio extends JpaRepository<PuntoControl, Long> {
	/**
	 * Busca un punto de control por una lista de psm
	 * @param listaPsm Lista de Psm
	 * @return Lista de punto de Control
	 */
	List<PuntoControl> findByListaPsm(List<Psm> listaPsm);
	
	@Query(value = "SELECT u.punto_control_id FROM PUNTO_CONTROL_LISTA_PSM u WHERE u.LISTA_PSM_ID = ?1", nativeQuery = true)
	Long findPuntoControlByPsm(long id);
	/**
	 * Busca por id el punto de control
	 * @param id El id del PuntoControl
	 * @return Un Punto de control
	 */
	PuntoControl findFirstById(long id);
}
