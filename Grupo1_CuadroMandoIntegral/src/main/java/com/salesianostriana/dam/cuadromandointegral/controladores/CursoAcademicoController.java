package com.salesianostriana.dam.cuadromandointegral.controladores;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cuadromandointegral.dto.CursoAcademicoDto;
import com.salesianostriana.dam.cuadromandointegral.dto.CursoAcademicoDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.errores.CursoAcademicoNoVacioException;
import com.salesianostriana.dam.cuadromandointegral.errores.EntityNotFoundException;
import com.salesianostriana.dam.cuadromandointegral.model.CursoAcademico;
import com.salesianostriana.dam.cuadromandointegral.servicios.CursoAcademicoServicio;

import lombok.RequiredArgsConstructor;
/**
 * Controlador para las operaciones con los Cursos Académicos
 * 
 * @author José Antonio Llamas
 *
 */
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/cursoacademico")
public class CursoAcademicoController {

	/**
	 * lo necesario para la clase
	 */
	private final CursoAcademicoServicio cursoAcademicoServicio;
	private final CursoAcademicoDtoConverter cursoAcademicoDtoConverter;

	/**
	 * Obtiene una lista de todos los cursos académicos
	 * @author José Antonio Llamas
	 * @return Lista de cursos académicos
	 */
	@GetMapping("/")
	public ResponseEntity<?> index() {
		List<CursoAcademico> result = cursoAcademicoServicio.findAll();
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			List<CursoAcademicoDto> dtoList = result.stream()
					.map(cursoAcademicoDtoConverter::convertCursoAcademicoToCursoAcademicoDto)
					.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}

	/**
	 * Crea un nuevo curso académico a partir de un dto
	 * 
	 * @author José Antonio Llamas
	 * @param cursoAcademicoDto El Dto que viene del formulario
	 * @return Curso académico
	 */
	@PostMapping("/")
	public ResponseEntity<?> nuevoCursoAcademico(@RequestBody CursoAcademicoDto cursoAcademicoDto) {
		CursoAcademico ca = cursoAcademicoServicio
				.save(cursoAcademicoDtoConverter.cursoAcademicoDtoToCursoAcademico(cursoAcademicoDto));
		return new ResponseEntity<CursoAcademico>(ca, HttpStatus.CREATED);
	}

	/**
	 * Elimina un curso académico comprobando que no tenga datos asociados
	 * @author José Antonio Llamas
	 * @param id El id del curso académico
	 * @return Excepciones si no existe o tiene datos asociados. Mensaje NoContent si lo ha eliminado correctamente.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (cursoAcademicoServicio.existsById(id)) {
			if (!cursoAcademicoServicio.findFirstById(id).getListaPsm().isEmpty()
					|| !cursoAcademicoServicio.findFirstById(id).getListaEtapa().isEmpty()
					|| !cursoAcademicoServicio.findFirstById(id).getListaValorIndicador().isEmpty()
					|| !cursoAcademicoServicio.findFirstById(id).getListaProceso().isEmpty()) {
				throw new CursoAcademicoNoVacioException(cursoAcademicoServicio.findFirstById(id).getNombreCursoAcademico());
			} else {
				cursoAcademicoServicio.deleteById(id);
				return ResponseEntity.noContent().build();
			}
		} else {
			throw new EntityNotFoundException(CursoAcademico.class, "id", id.toString());
		}
	}

}
