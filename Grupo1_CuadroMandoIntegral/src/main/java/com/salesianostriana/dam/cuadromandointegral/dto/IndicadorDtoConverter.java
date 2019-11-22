package com.salesianostriana.dam.cuadromandointegral.dto;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.Indicador;
import com.salesianostriana.dam.cuadromandointegral.model.IndicadorCentro;
import com.salesianostriana.dam.cuadromandointegral.model.IndicadorUnidad;
import com.salesianostriana.dam.cuadromandointegral.servicios.ColegioServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.ProcesoServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.UnidadServicio;

import lombok.RequiredArgsConstructor;

/**
 * Clase para convertido IndicadorDto a Indicador y viceversa
 * 
 * @author jldiez
 * 
 */

@Component
@RequiredArgsConstructor
public class IndicadorDtoConverter {
	
	/**
	 * lo necesario de la clase
	 */
	private final ProcesoServicio procesoServicio;
	private final ProcesoDtoConverter procesoDtoConverter;
	private final ColegioServicio colegioServicio;
	private final UnidadServicio unidadServicio;
	
	/**
	 * Convert para crear un nuevo indicador dependiendo del tipo
	 * @param indicadorDto dto a convertir en indicador
	 * @return Un Indicador
	 */
	public Indicador convertIndicadorDtoToNuevoIndicador(NuevoIndicadorDto indicadorDto) {
		if(indicadorDto.getTipoIndicador().equals("Centro")) {
			return IndicadorCentro.builder()
					.id(indicadorDto.getId())
					.proceso(procesoServicio.findById(indicadorDto.getProcesos()))
					.nombreIndicador(indicadorDto.getNombreIndicador())
					.esPorcentaje(indicadorDto.isEsPorcentaje())
					.listaColegios(colegioServicio.findColegiosByIds(indicadorDto.getColegios()))
					.build();
		} else {
			if(indicadorDto.isUnidadesTodas()) {
			return IndicadorUnidad.builder()
					.id(indicadorDto.getId())
					.proceso(procesoServicio.findById(indicadorDto.getProcesos()))
					.nombreIndicador(indicadorDto.getNombreIndicador())
					.esPorcentaje(indicadorDto.isEsPorcentaje())
					.listaUnidades(unidadServicio.findAll())
					.build();
			}
			else {
				return IndicadorUnidad.builder()
						.id(indicadorDto.getId())
						.proceso(procesoServicio.findById(indicadorDto.getProcesos()))
						.nombreIndicador(indicadorDto.getNombreIndicador())
						.esPorcentaje(indicadorDto.isEsPorcentaje())
						.listaUnidades(null)
						.build();
			}
		}
	}
	
		/**
		 * Convert para crear un nuevo IndicadorDto
		 * @param indicador a convertir en indicadorDto
		 * @return Un IndicadorDto
		 */
		public IndicadorDto convertIndicadorToIndicadorDto(Indicador indicador) {
			return IndicadorDto.builder()
					.id(indicador.getId())
					.proceso(procesoDtoConverter.convertProcesoToProcesoDto(indicador.getProceso()))
					.nombreIndicador(indicador.getNombreIndicador())
					.esPorcentaje(indicador.isEsPorcentaje())
					.build();
		}
				
}
