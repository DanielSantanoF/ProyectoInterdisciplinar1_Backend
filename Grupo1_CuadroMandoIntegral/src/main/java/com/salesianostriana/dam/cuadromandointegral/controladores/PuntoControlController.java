package com.salesianostriana.dam.cuadromandointegral.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cuadromandointegral.dto.PuntoControlDto;
import com.salesianostriana.dam.cuadromandointegral.dto.PuntoControlDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.errores.EntityNotFoundException;
import com.salesianostriana.dam.cuadromandointegral.model.PuntoControl;
import com.salesianostriana.dam.cuadromandointegral.servicios.PuntoControlServicio;

import lombok.RequiredArgsConstructor;
/**
 * Clase controller que gestiona los puntos de control
 * @author mlazaro
 *
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/puntosControl")
public class PuntoControlController {

	/**
	 * lo necesario para la clase
	 */
	private final PuntoControlServicio puntoControlServicio;
	private final PuntoControlDtoConverter puntoControlDtoConverter;
	
	/**
	 * Método que devuelve una lista de puntos de control
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> mostarPuntosDeControl(@PathVariable Long id) {
		if (id == null) {
			return ResponseEntity.noContent().build();
		} else {
			List<PuntoControl> lista = puntoControlServicio.findAllByCursoAcademicoId(id);
			List<PuntoControlDto> listaDto = new ArrayList<>();
			for (PuntoControl puntoControl2 : lista) {
				listaDto.add(puntoControlDtoConverter.convertPuntoControlToPuntoControlDto(puntoControl2));
			}
			return new ResponseEntity<List<PuntoControlDto>>(listaDto, HttpStatus.OK);
		}
	}
	
	
	/**
	 * Método que muestra los puntos de control vacío
	 * @return
	 */
	@GetMapping("/puntos/vacios")
	public ResponseEntity<?> mostarPuntosDeControlVacios() {
			List<PuntoControl> lista = puntoControlServicio.findAll();
			List<PuntoControlDto> listaDto = new ArrayList<>();
			for (PuntoControl puntoControl2 : lista) {
				if(puntoControl2.getListaPsm().isEmpty()) {
					listaDto.add(puntoControlDtoConverter.convertPuntoControlVacioToPuntoControlDto(puntoControl2));
				}
			}
			return new ResponseEntity<List<PuntoControlDto>>(listaDto, HttpStatus.OK);
		}

	/**
	 * Método que borra un punto de control según su id
	 * @param id
	 */
	@DeleteMapping("/delete/{id}")
	void delete(@PathVariable Long id) {
		if (puntoControlServicio.existsById(id)) {
			puntoControlServicio.deleteById(id);
		} else {
			throw new EntityNotFoundException(PuntoControl.class, "id", id.toString());
		}
	}
	
	/**
	 * Método que añade un nuevo punto de control
	 * @param puntoControlDto
	 * @return
	 */
	@PostMapping("/new")
	public ResponseEntity<?> crearPuntoControl(@RequestBody PuntoControlDto puntoControlDto){
			PuntoControl pc = puntoControlServicio.newPuntoControl(puntoControlDto);	
			return new ResponseEntity<PuntoControlDto>(puntoControlDtoConverter.convertPuntoControlToPuntoControlDto(pc),HttpStatus.CREATED);
	}
	
	
}
