package com.salesianostriana.dam.cuadromandointegral.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicadorCentro;
import com.salesianostriana.dam.cuadromandointegral.repositorios.ValorIndicadorCentroRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de ValorIndicadorCentro
 * @author José Antonio Llamas
 *
 */
@Service
@RequiredArgsConstructor
public class ValorIndicadorCentroServicio
		extends ServicioBase<ValorIndicadorCentro, Long, ValorIndicadorCentroRepositorio> {

	/**
	 * Busca todos los ValorIndicadorCentro de un determinado Psm
	 * 
	 * @author José Antonio Llamas
	 * 
	 * @param psm El Psm referencia
	 * @return Lista de ValorIndicadorCentro
	 */
	public List<ValorIndicadorCentro> findAllByPsm(Psm psm) {
		return repositorio.findAllByPsm(psm);
	}

	/**
	 * Comprueba que un id de ValorIndicadorCentro existe
	 * 
	 * @author José Antonio Llamas
	 * 
	 * @param id La id a buscar
	 * @return booleano indicando si la id existe o no
	 */
	public boolean existsById(Long id) {
		return repositorio.existsById(id);
	}

	/**
	 * Busca todos los ValoresIndicadoresCentro relacionados con un IndicadorCentro
	 * o Indicador (tienen el mismo Id) concreto.
	 * 
	 * @author José Antonio Llamas
	 * @param id Id del IndicadorCentro (que es el mismo que el del Indicador)
	 * @return Lista de los Valores Indicadores Centro existentes para un Indicador
	 */
	public List<ValorIndicadorCentro> findAllByIndicadorCentroId(long id) {
		return repositorio.findAllByIndicadorCentroId(id);
	}

	/**
	 * Si hay algún ValorIndicadorCentro relacionado con un Psm, lo elimina de la
	 * lista de ValorIndicadorCentro del Colegio correspondiente y, después, lo
	 * elimina.
	 * 
	 * @author José Antonio Llamas
	 * @param psm El Psm de referencia
	 */
	public void eliminarValoresIndicadorCentroPorPsm(Psm psm) {
		if (!findAllByPsm(psm).isEmpty()) {
			for (ValorIndicadorCentro vic : findAllByPsm(psm)) {
				psm.getColegio().deleteValorIndicadorCentro(vic);
				delete(vic);
			}
		}
	}

}
