package com.salesianostriana.dam.cuadromandointegral.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cuadromandointegral.dto.NuevaUnidadDto;
import com.salesianostriana.dam.cuadromandointegral.dto.UnidadDto;
import com.salesianostriana.dam.cuadromandointegral.dto.UnidadDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.errores.EntityNotFoundException;
import com.salesianostriana.dam.cuadromandointegral.model.Indicador;
import com.salesianostriana.dam.cuadromandointegral.model.Unidad;
import com.salesianostriana.dam.cuadromandointegral.servicios.CursoServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.UnidadServicio;

import lombok.RequiredArgsConstructor;
/**
 * 
 * @author jldiez Controlador de peticiones sobre Unidad
 *
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/unidad")
public class UnidadController {
	
	/**
	 * lo necesario para la clase
	 */
	private final UnidadServicio unidadServicio;
	private final UnidadDtoConverter unidadDtoConverter;
	private final CursoServicio cursoServicio;
	
	/**
	 * Controlador que devuelve una lista de todas las unidades
	 * @return Lista de Dto de unidad
	 */
	@GetMapping("/")
	public ResponseEntity<?>mostrarUnidades(){
		List<Unidad> lista = unidadServicio.findAll();
		if (lista.isEmpty())
			return ResponseEntity.noContent().build();
		else {
			List<UnidadDto> listaDto = new ArrayList<>();
			for (Unidad unidadl2 : lista) {
				listaDto.add(unidadDtoConverter.convertUnidadToUnidadDto(unidadServicio.findById(unidadl2.getId())));
			}
			return new ResponseEntity<List<UnidadDto>>(listaDto, HttpStatus.OK);
		}
	}
	
	/**
	 * Controlador que devuelve una lista de etapas  que pertenecen
	 * a un curso
	 * @param id Id del Curso  
	 * @return Lista de Dto de Etapa
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> mostarUnidadPorCurso(@PathVariable Long id) {
		List<Unidad> lista = unidadServicio.findAllByCursoId(id);
		if (id == null) {
			return ResponseEntity.noContent().build();
		} else {
			List<UnidadDto> listaDto = new ArrayList<>();
			for (Unidad unidadl2 : lista) {
				listaDto.add(unidadDtoConverter.convertUnidadToUnidadDto(unidadServicio.findById(unidadl2.getId())));
			}
			return new ResponseEntity<List<UnidadDto>>(listaDto, HttpStatus.OK);
		}
	}
	
	/**
	 * Controlador para crear una nueva unidad
	 * @param unidadDto de una nueva unidad
	 * @return dto de la nueva unidad creada
	 */
	@PostMapping("/newunidad")
	public ResponseEntity<?> nuevoUnidad(@RequestBody NuevaUnidadDto unidadDto) {
		Unidad u = unidadDtoConverter.convertUnidadDtoToUnidad(unidadDto);
		unidadServicio.save(u);
		return new ResponseEntity<NuevaUnidadDto>(unidadDto, HttpStatus.CREATED);
	}
	
	/**
	 * controlador para editar una unidad por su id
	 * @param id de la unidad que vamos a editar
	 * @param unidad la que editamos
	 * @return dto de la unidad modificada
	 */
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editarUnidad(@PathVariable Long id, @Valid @RequestBody UnidadDto unidad) {
		Unidad u = unidadServicio.findById(id);
		if (u == null) {
			throw new EntityNotFoundException(Indicador.class, "id", id.toString());
		}else {
			u.setNombreUnidad(unidad.getNombreUnidad());
			u.setCurso(cursoServicio.findById(unidad.getCurso()));
			unidadServicio.edit(u);
			return new ResponseEntity<UnidadDto>(unidadDtoConverter.convertUnidadToUnidadDto(u), HttpStatus.OK);
		}
	}
	
	/**
	 * Controlador para borrar una unidad por id
	 * @param id 
	 * @return ResponseEntity sin contenido
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUnidad(@PathVariable Long id) {
		Unidad u = unidadServicio.findById(id);
		if (u == null) {
			throw new EntityNotFoundException(Indicador.class, "id", id.toString());
		}else {
			unidadServicio.deleteById(id);
			return ResponseEntity.noContent().build();
		}
	}

}
