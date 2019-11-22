package com.salesianostriana.dam.cuadromandointegral.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicadorUnidad;
import com.salesianostriana.dam.cuadromandointegral.repositorios.ValorIndicadorUnidadRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de ValorIndicadorUnidad
 * 
 * @author José Antonio Llamas
 *
 */
@Service
@RequiredArgsConstructor
public class ValorIndicadorUnidadServicio
		extends ServicioBase<ValorIndicadorUnidad, Long, ValorIndicadorUnidadRepositorio> {

	/**
	 * Busca todos los ValorIndicadorUnidad de un determinado Psm
	 * 
	 * @author José Antonio Llamas
	 * 
	 * @param psm El Psm referencia
	 * @return Lista de ValorIndicadorUnidad
	 */
	public List<ValorIndicadorUnidad> findAllByPsm(Psm psm) {
		return repositorio.findAllByPsm(psm);
	}

	/**
	 * Si hay algún ValorIndicadorUnidad relacionado con un Psm, lo elimina de la
	 * lista de ValorIndicadorUnidad de la Unidad correspondiente y, después, lo
	 * elimina.
	 * 
	 * @author José Antonio Llamas
	 * @param psm El Psm de referencia
	 */
	public void eliminarValoresIndicadorUnidadPorPsm(Psm psm) {
		if (!findAllByPsm(psm).isEmpty()) {
			for (ValorIndicadorUnidad viu : findAllByPsm(psm)) {
				viu.getUnidad().deleteValorIndicadorUnidad(viu);
				delete(viu);
			}
		}
	}

	/**
	 * 
	 * Busca todos los ValoresIndicadoresUnidad relacionados con un IndicadorUnidad
	 * o Indicador (tienen el mismo Id) concreto
	 * 
	 * @author José Antonio Llamas
	 * @param id Id del IndicadorUnidad (que es el mismo que el del Indicador)
	 * @return Lista de los Valores Indicadores Unidad existentes para un Indicador
	 */
	public List<ValorIndicadorUnidad> findAllByIndicadorUnidadId(long id) {
		return repositorio.findAllByIndicadorUnidadId(id);
	}
}
