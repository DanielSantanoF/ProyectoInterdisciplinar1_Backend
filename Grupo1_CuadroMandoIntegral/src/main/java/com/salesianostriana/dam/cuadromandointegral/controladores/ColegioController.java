package com.salesianostriana.dam.cuadromandointegral.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cuadromandointegral.dto.ColegioDto;
import com.salesianostriana.dam.cuadromandointegral.dto.ListaColegiosDtoConverter;

import lombok.RequiredArgsConstructor;

/**
 * Controlador de peticiones sobre colegios
 * 
 * @author Daniel Santano Fernández 
 *
 */

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/colegio")
public class ColegioController {

	/**
	 * Atributos de la clase
	 */
	private final ListaColegiosDtoConverter listaColegiosDtoConverter;
	
	/**
	 * Metodo para obtener todos los colegios de nuestra api
	 * @return List ColegioDto con todos los colegios
	 */
	@GetMapping("/obtenerColegios")
	ResponseEntity<?> obtenerColegios() {
		List<ColegioDto> listaColegios = listaColegiosDtoConverter.convertListaColegioToListaColegioDto();
		//AÑADIMOS ESTAS LINEAS DE CODIGO PARA HACER MÁS DE 1 TEST A ESTE CONTROLADOR
		if(listaColegios.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<List<ColegioDto>>(listaColegios, HttpStatus.OK);
		}
		
	}
	
}
