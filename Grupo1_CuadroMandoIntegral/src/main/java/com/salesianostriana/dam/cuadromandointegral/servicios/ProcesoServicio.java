package com.salesianostriana.dam.cuadromandointegral.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.dto.ProcesoDto;
import com.salesianostriana.dam.cuadromandointegral.model.CursoAcademico;
import com.salesianostriana.dam.cuadromandointegral.model.Proceso;
import com.salesianostriana.dam.cuadromandointegral.repositorios.ProcesoRepositorio;
/**
 * Servicio del modelo proceso
 * @author mlazaro
 *
 */
@Service
public class ProcesoServicio extends ServicioBase<Proceso, Long, ProcesoRepositorio> {

	@Autowired
	private CursoAcademicoServicio cursoAcademicoServicio;
	
	/**
	 * Busca los procesos por el id de curso Academico
	 * @param id
	 * @return
	 */
	public List<Proceso> findAllByCursoAcademicoId(Long id) {
		return repositorio.findAllByCursoAcademicoId(id);
	}
	/**
	 * Comprueba si existe por su id
	 * @param id
	 * @return
	 */
	public boolean existsById(Long id) {
		return repositorio.existsById(id);
	}
	/**
	 * Crea y guarda un nuevo proceso
	 * @param procesoDto
	 * @return
	 */
	public Proceso newProceso(ProcesoDto procesoDto) {
		CursoAcademico c = cursoAcademicoServicio.findById(procesoDto.getCursoAcademico().getId());
		Proceso p = Proceso.builder().id(procesoDto.getId()).nombreProceso(procesoDto.getNombre())
				.tipo(procesoDto.getTipo())
				.cursoAcademico(
						c)
				.build();
		return repositorio.save(p);
	}

}
