package com.salesianostriana.dam.cuadromandointegral.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicador;
import com.salesianostriana.dam.cuadromandointegral.repositorios.ValorIndicadorRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de ValorIndicador
 * @author José Antonio Llamas
 *
 */
@Service
@RequiredArgsConstructor
public class ValorIndicadorServicio extends ServicioBase<ValorIndicador, Long, ValorIndicadorRepositorio> {

	/**
	 * Busca todos los ValorIndicador de un Psm
	 * @param id El id del Psm
	 * @return Lista de ValorIndicador
	 */
	public List<ValorIndicador> findAllByPsmId(long id) {
		return repositorio.findAllByPsmId(id);
	}

	/**
	 * Elimina todos los valores indicadores de un Psm
	 *  
	 * @param id El id del Psm
	 */
	public void eliminarValoresIndicadorPorPsm(long id) {
		if (!findAllByPsmId(id).isEmpty()) {
			for (ValorIndicador vi : findAllByPsmId(id)) {
				delete(vi);
			}
		}
	}

}
