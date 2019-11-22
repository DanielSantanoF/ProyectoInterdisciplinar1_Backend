package com.salesianostriana.dam.cuadromandointegral.servicios;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.dto.PsmDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.dto.PuntoControlDto;
import com.salesianostriana.dam.cuadromandointegral.model.PuntoControl;
import com.salesianostriana.dam.cuadromandointegral.repositorios.PuntoControlRepositorio;
/**
 * Servicio del modelo Punto de control
 * @author mlazaro
 *
 */
@Service
public class PuntoControlServicio extends ServicioBase<PuntoControl, Long, PuntoControlRepositorio> {

	@Autowired
	private PsmServicio psmServicio;
	@Autowired
	private ColegioServicio colegioServicio;
	@Autowired
	private PsmDtoConverter psmDtoConverter;

	/**
	 * Busca los puntos de control por el id de curso académico
	 * @param id
	 * @return
	 */
	public List<PuntoControl> findAllByCursoAcademicoId(Long id) {

		return repositorio.findByListaPsm(psmServicio.findAllByCursoAcademicoId(id));
	}
	
	/**
	 * Comprueba si existe según su id
	 * @param id
	 * @return
	 */
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return repositorio.existsById(id);
	}
	/**
	 * Crea y guarda un punto de control
	 * @param puntoControlDto
	 * @return
	 */
	public PuntoControl newPuntoControl(PuntoControlDto puntoControlDto) {
		
		PuntoControl newPuntoControl = PuntoControl.builder().id(puntoControlDto.getId())
				.fecha(puntoControlDto.getFecha()).colegio(colegioServicio.findById(puntoControlDto.getColegioDto().getId()))
				.listaPsm(puntoControlDto.getPsm().stream().map(psmDtoConverter::convertPsmDtoToPsm)
						.collect(Collectors.toList()))
				.build();

		return repositorio.save(newPuntoControl);
	}
	/**
	 * Encuentra un punto de control por un psm
	 * @param id
	 * @return
	 */
	public PuntoControl findPuntoControlByPsm(long id) {
		long idpc=repositorio.findPuntoControlByPsm(id);
		return repositorio.findFirstById(idpc);
	}
}
