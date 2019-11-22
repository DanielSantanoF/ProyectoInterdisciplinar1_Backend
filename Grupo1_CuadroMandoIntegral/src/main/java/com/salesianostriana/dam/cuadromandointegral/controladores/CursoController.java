package com.salesianostriana.dam.cuadromandointegral.controladores;

import java.util.ArrayList;
import java.util.List;

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

import com.salesianostriana.dam.cuadromandointegral.dto.CursoDto;
import com.salesianostriana.dam.cuadromandointegral.dto.CursoDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.errores.EntityNotFoundException;
import com.salesianostriana.dam.cuadromandointegral.model.Curso;
import com.salesianostriana.dam.cuadromandointegral.model.Indicador;
import com.salesianostriana.dam.cuadromandointegral.servicios.CursoServicio;

import lombok.RequiredArgsConstructor;
/**
 * 
 * @author Miguel Lázaro Domínguez y José Luis Diez Cortés
 * 
 * Controlador que gestiona los cursos
 *
 */
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/cursos")
public class CursoController {
	
	/**
	 * lo necesario para la clase
	 */
	private final CursoServicio cursoServicio;
	private final CursoDtoConverter cursoConverter;
	
	/**
	 * Método que devuelve una lista de cursos dependiendo del id de curso académico
	 * @param id
	 * @return List<Curso>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> mostrarCursos(@PathVariable Long id){
		
		if(id==null) {
			return ResponseEntity.noContent().build();
		}else {
			List<Curso> cursos = cursoServicio.fiNdAllByEtapaId(id);
			List<CursoDto> cursosDto = new ArrayList<>();
			for (Curso curso : cursos) {
				cursosDto.add(cursoConverter.convertCursoToCursoDto(curso));
			}
			return new ResponseEntity<List<CursoDto>>(cursosDto,HttpStatus.OK);
		}
	}
	
	/**
	 * Método que borra un curso segun su id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	// @PostAuthorize("!hasAuthority('SUPER_ADMIN')")
	public ResponseEntity<?> deleteCurso(@PathVariable Long id) {
		Curso i = cursoServicio.findById(id);
		if (i == null) {
			throw new EntityNotFoundException(Indicador.class, "id", id.toString());
		}else {
			cursoServicio.deleteById(id);
			return ResponseEntity.noContent().build();
		}
	}
	/**
	 * Método que añade un curso	
	 * @param cursoDto
	 * @return
	 */
	@PostMapping("/new")
	public ResponseEntity<?> crearCurso(@RequestBody CursoDto cursoDto){
			Curso c = cursoServicio.newCurso(cursoDto);	
			return new ResponseEntity<CursoDto>(cursoConverter.convertCursoToCursoDto(c),HttpStatus.CREATED);
	}

}
