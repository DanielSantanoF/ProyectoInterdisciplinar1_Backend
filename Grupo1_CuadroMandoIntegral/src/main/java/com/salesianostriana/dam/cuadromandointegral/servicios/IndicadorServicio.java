package com.salesianostriana.dam.cuadromandointegral.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.dto.ListaValoresIndicadorDto;
import com.salesianostriana.dam.cuadromandointegral.dto.ValorIndicadorCentroDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.dto.ValorIndicadorUnidadDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.errores.EntityNotFoundException;
import com.salesianostriana.dam.cuadromandointegral.model.Indicador;
import com.salesianostriana.dam.cuadromandointegral.repositorios.IndicadorRepositorio;

/**
 * Servicio de indicador
 * 
 * @author Jose Antonio Llamas
 *
 */
@Service
public class IndicadorServicio extends ServicioBase<Indicador, Long, IndicadorRepositorio> {

	private static IndicadorCentroServicio indicadorCentroServicio;
	private static ValorIndicadorCentroServicio valorIndicadorCentroServicio;
	private static ValorIndicadorCentroDtoConverter valorIndicadorCentroDtoConverter;
	private static ValorIndicadorUnidadServicio valorIndicadorUnidadServicio;
	private static ValorIndicadorUnidadDtoConverter valorIndicadorUnidadDtoConverter;

	@Autowired
	public void setServicios(IndicadorCentroServicio indicadorCentroServicio,
			ValorIndicadorCentroServicio valorIndicadorCentroServicio,
			ValorIndicadorCentroDtoConverter valorIndicadorCentroDtoConverter,
			ValorIndicadorUnidadServicio valorIndicadorUnidadServicio,
			ValorIndicadorUnidadDtoConverter valorIndicadorUnidadDtoConverter) {

		IndicadorServicio.indicadorCentroServicio = indicadorCentroServicio;
		IndicadorServicio.valorIndicadorCentroServicio = valorIndicadorCentroServicio;
		IndicadorServicio.valorIndicadorCentroDtoConverter = valorIndicadorCentroDtoConverter;
		IndicadorServicio.valorIndicadorUnidadServicio = valorIndicadorUnidadServicio;
		IndicadorServicio.valorIndicadorUnidadDtoConverter = valorIndicadorUnidadDtoConverter;
	}

	/**
	 * Busca una lista de indicadores por su nombre (habrá un indicador con el mismo
	 * nombre por cada curso académico en el que se haya incluido)
	 * 
	 * @author José Antonio Llamas
	 * @param name Nombre del Indicador
	 * @return El indicador completo
	 */
	public List<Indicador> findByNombreIndicador(String name) {
		return repositorio.findByNombreIndicador(name);
	}

	/**
	 * Encuentra los Indicadores de un Proceso determinado
	 * 
	 * @param id El id del proceso
	 * @return La lista de Indicadores
	 */
	public List<Indicador> findByProcesoId(Long id) {
		return repositorio.findByProcesoId(id);
	}

	/**
	 * Busca un Indicador según su Id;
	 * 
	 * @author José Antonio Llamas
	 * @param id El id del indicador buscado
	 * @return Indicador
	 */
	public Indicador findFirstById(long id) {
		return repositorio.findFirstById(id);
	}

	/**
	 * Método que devuelve un ResponseEntity con una lista de los valores reales y conformes para un
	 * indicador en todos los cursos académicos
	 * 
	 * @author José Antonio Llamas
	 * @param id Id del indicador a mostrar la tendencia
	 * @return Lista de Dto de ValorIndicador
	 */
	public ResponseEntity<?> mostrarValoresIndicadoresPorIdDeIndicador(Long id) {

		Indicador i = findFirstById(id);
		List<Indicador> listaI = findByNombreIndicador(i.getNombreIndicador());
		if (listaI.isEmpty()) {
			throw new EntityNotFoundException(Indicador.class, "id", id.toString());
		} else {
			List<Long> listaIdIndicador = new ArrayList<>();

			for (Indicador ind : listaI) {
				listaIdIndicador.add(ind.getId());
			}

			if (indicadorCentroServicio.existsById(id)) {
				List<ListaValoresIndicadorDto> listaDto = new ArrayList<>();

				for (Long idValorIndicadorCentro : listaIdIndicador) {

					listaDto.add(valorIndicadorCentroDtoConverter.convertValorIndicadorCentroToListaValoresIndicadorDto(
							valorIndicadorCentroServicio.findById(idValorIndicadorCentro)));
				}
				return new ResponseEntity<List<ListaValoresIndicadorDto>>(listaDto, HttpStatus.OK);
			} else {
				List<ListaValoresIndicadorDto> listaDto = new ArrayList<>();

				for (Long idValorIndicadorUnidad : listaIdIndicador) {

					listaDto.add(valorIndicadorUnidadDtoConverter.convertValorIndicadorUnidadToListaValoresIndicadorDto(
							valorIndicadorUnidadServicio.findById(idValorIndicadorUnidad)));
				}
				return new ResponseEntity<List<ListaValoresIndicadorDto>>(listaDto, HttpStatus.OK);
			}
		}
	}
}
