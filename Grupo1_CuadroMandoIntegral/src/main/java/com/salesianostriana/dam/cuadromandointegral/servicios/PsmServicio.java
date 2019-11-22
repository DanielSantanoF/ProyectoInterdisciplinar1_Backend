package com.salesianostriana.dam.cuadromandointegral.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.enums.Evaluacion;
import com.salesianostriana.dam.cuadromandointegral.repositorios.PsmRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de psm
 * 
 * @author Jose Antonio Llamas
 *
 */

@Service
@RequiredArgsConstructor
public class PsmServicio extends ServicioBase<Psm, Long, PsmRepositorio> {

	/**
	 * lo necesario para la clase
	 */
	private static PuntoControlServicio puntoControlServicio;
	private static ValorIndicadorUnidadServicio valorIndicadorUnidadServicio;
	private static ValorIndicadorCentroServicio valorIndicadorCentroServicio;
	private static ValorIndicadorServicio valorIndicadorServicio;
	
	/**
	 * contructor de los servicio
	 * @param puntoControlServicio  puntoControlServicio
	 * @param valorIndicadorUnidadServicio valorIndicadorUnidadServicio
	 * @param valorIndicadorCentroServicio valorIndicadorCentroServicio
	 * @param valorIndicadorServicio valorIndicadorServicio
	 */
	@Autowired
	public void setServicios(PuntoControlServicio puntoControlServicio, ValorIndicadorUnidadServicio valorIndicadorUnidadServicio,
			ValorIndicadorCentroServicio valorIndicadorCentroServicio, ValorIndicadorServicio valorIndicadorServicio) {

		PsmServicio.puntoControlServicio = puntoControlServicio;
		PsmServicio.valorIndicadorUnidadServicio = valorIndicadorUnidadServicio;
		PsmServicio.valorIndicadorCentroServicio = valorIndicadorCentroServicio;
		PsmServicio.valorIndicadorServicio = valorIndicadorServicio;
	}
	
	/**
	 * llama al metodo del repo
	 * @param id id del psm
	 * @return psm encontrado
	 */
	public Psm findFirstById(Long id) {
		return repositorio.findFirstById(id);
	}

	/**
	 * llama al metodo del repo
	 * @param id de curso academido
	 * @return lista de psm
	 */
	public List<Psm> findAllByCursoAcademicoId(Long id) {
		return repositorio.findAllByCursoAcademicoId(id);
	}

	/**
	 * llama al metodo del repo
	 * @param id id del psm
	 * @return boolean segun si existe el psm o no
	 */
	public boolean existsById(Long id) {
		return repositorio.existsById(id);
	}

	/**
	 * llama al metodo del repo
	 * @param psmId id del psm
	 * @param caId curso academico id 
	 * @param evaluacion ev
	 * @return el psm encontrado
	 */
	public Psm findFirstByIdAndCursoAcademicoIdAndEvaluacion(long psmId, long caId, Evaluacion evaluacion) {
		return repositorio.findFirstByIdAndCursoAcademicoIdAndEvaluacion(psmId, caId, evaluacion);
	}

	/**
	 * Eliminar psm segun el id
	 * @param id del psm
	 */
	public boolean eliminarPsmPorId(long id) {
		//CAMBIAMOS EL METODO PARA PODER HACERLE MAS TEST
		Psm psm=findFirstById(id);
		if(psm == null) {
			psm.getColegio().deletePsm(psm);
			puntoControlServicio.findPuntoControlByPsm(id).deletePsm(findFirstById(id));
			deleteById(id);
			valorIndicadorUnidadServicio.eliminarValoresIndicadorUnidadPorPsm(psm);
			valorIndicadorCentroServicio.eliminarValoresIndicadorCentroPorPsm(psm);
			valorIndicadorServicio.eliminarValoresIndicadorPorPsm(id);
			repositorio.delete(psm);
			return true;
		} else {
			return false;
		}
		
	}

}
