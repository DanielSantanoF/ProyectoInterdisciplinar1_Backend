package com.salesianostriana.dam.cuadromandointegral.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cuadromandointegral.dto.IndicadorDto;
import com.salesianostriana.dam.cuadromandointegral.dto.IndicadorDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.dto.NuevoIndicadorDto;
import com.salesianostriana.dam.cuadromandointegral.errores.EntityNotFoundException;
import com.salesianostriana.dam.cuadromandointegral.model.Indicador;
import com.salesianostriana.dam.cuadromandointegral.servicios.IndicadorServicio;

import lombok.RequiredArgsConstructor;
/**
 * 
 * @author jldiez Controlador de peticiones sobre Indicadores
 *
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/indicador")
@Validated
public class IndicadorController {
	
	/**
	 * lo necesario para la clase
	 */
	private final IndicadorServicio indicadorServicio;
	private final IndicadorDtoConverter indicadorDtoConverter;
	
	/**
	 * Controlador que devuelve una lista de IndicadoresDto
	 * @return Lista de Dto de Indicador
	 */
	@GetMapping("/indicadores")
	public ResponseEntity<?>mostrarIndicadores(){
		List<Indicador> lista = indicadorServicio.findAll();
		if (lista.isEmpty())
			return ResponseEntity.noContent().build();
		else {
			List<IndicadorDto> listaDto = new ArrayList<>();
			for (Indicador indicadol2 : lista) {
				listaDto.add(indicadorDtoConverter.convertIndicadorToIndicadorDto(indicadorServicio.findById(indicadol2.getId())));
			}
			return new ResponseEntity<List<IndicadorDto>>(listaDto, HttpStatus.OK);
		}
	}
	
	/**
	 * Controlador que devuelve una lista de etapas  que pertenecen
	 * a un proceso
	 * @param id Id del Proceso 
	 * @return Lista de Dto de Indicadores
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?>mostrarIndicadoresPorProceso(@PathVariable Long id){
		List<Indicador> lista = indicadorServicio.findByProcesoId(id);
		if (id == null) {
			return ResponseEntity.noContent().build();
		} else {
			List<IndicadorDto> listaDto = new ArrayList<>();
			for (Indicador indicadol2 : lista) {
				listaDto.add(indicadorDtoConverter.convertIndicadorToIndicadorDto(indicadorServicio.findById(indicadol2.getId())));
			}
			return new ResponseEntity<List<IndicadorDto>>(listaDto, HttpStatus.OK);
		}
	}
	
	
	/**
	 * Controlador para crear un nuevo indicador
	 * @param Dto de un nuevo indicador
	 * @return Lista de Dto de Etapa
	 */
	
	@PostMapping("/newindicador")
	public ResponseEntity<?> nuevoIndicador(@RequestBody NuevoIndicadorDto indicadorDto) {
		if ( indicadorDto.getNombreIndicador().isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
		indicadorServicio.save(indicadorDtoConverter.convertIndicadorDtoToNuevoIndicador(indicadorDto));
		return new ResponseEntity<NuevoIndicadorDto>(indicadorDto,HttpStatus.CREATED);
		}
	}
	
	/**
	 * Controlador para editar un indicador por id
	 * @param id Id del Proceso, indicador para modificar 
	 * @return  Dto de Indicadores editado
	 */
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editarIndicador(@PathVariable Long id, @Valid @RequestBody Indicador indicador) {
		Indicador i = indicadorServicio.findById(id);
		if (i == null) {
			throw new EntityNotFoundException(Indicador.class, "id", id.toString());
		}else {
			i.setNombreIndicador(indicador.getNombreIndicador());
			i.setEsPorcentaje(indicador.isEsPorcentaje());
			i.setProceso(indicador.getProceso());
			indicadorServicio.edit(i);
			return new ResponseEntity<IndicadorDto>(indicadorDtoConverter.convertIndicadorToIndicadorDto(i), HttpStatus.OK);
		}
	}
	
	/**
	 * Controlador para borrar un indicador por su id
	 * @param id Id de la etapa que queremos borrar 
	 * @return ResponseEntity sin contenido
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteIndicador(@PathVariable Long id) {
		Indicador i = indicadorServicio.findById(id);
		if (i == null) {
			throw new EntityNotFoundException(Indicador.class, "id", id.toString());
		}else {
			indicadorServicio.deleteById(id);
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * Controlador que devuelve una lista de los valores reales y conformes para un
	 * indicador en todos los cursos académicos
	 * 
	 * @author José Antonio Llamas
	 * @param id Id del indicador a mostrar la tendencia
	 * @return Lista de Dto de ValorIndicador
	 */
	@GetMapping("/tendencia/{id}")
	public ResponseEntity<?> mostrarValoresIndicadoresPorIdDeIndicador(@PathVariable Long id) {
		return indicadorServicio.mostrarValoresIndicadoresPorIdDeIndicador(id);
	}
}
