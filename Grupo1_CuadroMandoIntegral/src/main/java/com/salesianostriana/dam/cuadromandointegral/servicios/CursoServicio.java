package com.salesianostriana.dam.cuadromandointegral.servicios;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.dto.CursoDto;
import com.salesianostriana.dam.cuadromandointegral.dto.UnidadDto;
import com.salesianostriana.dam.cuadromandointegral.model.Curso;
import com.salesianostriana.dam.cuadromandointegral.model.Etapa;
import com.salesianostriana.dam.cuadromandointegral.model.Unidad;
import com.salesianostriana.dam.cuadromandointegral.repositorios.CursoRepositorio;
/**
 * Servicio del modelo curso
 * @author mlazaro
 *
 */
@Service
public class CursoServicio extends ServicioBase<Curso,Long,CursoRepositorio>{
	@Autowired
	private EtapaServicio etapaServicio;
	@Autowired
	private UnidadServicio unidadServicio;
	/**
	 * Busca una lista de cursos por el id de la etapa
	 * @param id El id de la etapa
	 * @return Lista de cursos
	 */
	public List<Curso> fiNdAllByEtapaId(Long id) {
		List<Curso> cursos = new ArrayList<>();
		List<Etapa> etapas = etapaServicio.findByCursoAcademicoId(id);
		for (Etapa etapa : etapas) {
			cursos.addAll(repositorio.findAllByEtapaId(etapa.getId()));
		}
		return cursos;
	}
	/**
	 * Comprueba si existe el curso según su id
	 * 
	 * @param id El id del curso
	 * @return Booleano indicando si existe
	 */
	public boolean existsById(Long id) {
		return repositorio.existsById(id);
	}
	/**
	 * Crea y guarda un nuevo curso
	 * @param cursoDto
	 * @return
	 */
	public Curso newCurso(CursoDto cursoDto) {
		List<Unidad> unidades = new ArrayList<>();
		List<UnidadDto> unidadesDto = cursoDto.getUnidades();
		if(unidadesDto!=null) {
			for (UnidadDto unidad : unidadesDto) {
				
				unidades.add(unidadServicio.findById(unidad.getId()));
			}
		}
		Curso c = Curso.builder()
				.id(cursoDto.getId())
				.nombreCurso(cursoDto.getNombreCurso())
				.peso(cursoDto.getPeso())
				.etapa(etapaServicio.findByNombreSubEtapa(cursoDto.getNombreEtapa()))
				.unidades(unidades)
				.build();
		return repositorio.save(c);
	}
	/**
	 * Busca por un id de etapa
	 * @param id
	 * @return
	 */
	public List<Curso> findAllByEtapaId(Long id) {
		return repositorio.findAllByEtapaId(id);
	}

}
