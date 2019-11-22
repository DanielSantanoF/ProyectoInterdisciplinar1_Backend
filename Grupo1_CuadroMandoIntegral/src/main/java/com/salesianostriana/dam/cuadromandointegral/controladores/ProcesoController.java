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

import com.salesianostriana.dam.cuadromandointegral.dto.ProcesoDto;
import com.salesianostriana.dam.cuadromandointegral.dto.ProcesoDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.errores.EntityNotFoundException;
import com.salesianostriana.dam.cuadromandointegral.model.Etapa;
import com.salesianostriana.dam.cuadromandointegral.model.Proceso;
import com.salesianostriana.dam.cuadromandointegral.servicios.ProcesoServicio;

import lombok.RequiredArgsConstructor;
/**
 * Clase controller que gestiona los procesos
 * @author Miguel Lázaro Domínguez
 *
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/procesos")
public class ProcesoController {

	/**
	 * lo necesario para la clase
	 */
	private final ProcesoServicio procesoServicio;
	private final ProcesoDtoConverter procesoDtoConverter;
	
	/**
	 * Método que devuelve un listado de procesos segun el id del curso académico
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> mostrarProcesos(@PathVariable Long id) {
		List<Proceso> lista = procesoServicio.findAllByCursoAcademicoId(id);
		if (id == null) {
			return ResponseEntity.noContent().build();
		} else {
			
			List<ProcesoDto> listaDto = new ArrayList<>();
			for (Proceso proceso : lista) {
				listaDto.add(procesoDtoConverter.convertProcesoToProcesoDto(proceso));
			}
			return new ResponseEntity<List<ProcesoDto>>(listaDto, HttpStatus.OK);
		}
	}
	
	//controlador creado para testearlo
	@DeleteMapping("/delete/{id}")
	// @PostAuthorize("!hasAuthority('SUPER_ADMIN')")
	public ResponseEntity<?> deleteEtapa(@PathVariable Long id) {
		Proceso i = procesoServicio.findById(id);
		if (i == null) {
			throw new EntityNotFoundException(Etapa.class, "id", id.toString());
		}else {
			procesoServicio.deleteById(id);
			return ResponseEntity.noContent().build();
		}
	}


	/**
	 * Método que elimina un proceso según su id
	 * @param id
	 */
//	@DeleteMapping("/delete/{id}")
//	void delete(@PathVariable Long id) {
//		if (procesoServicio.existsById(id)) {
//			procesoServicio.deleteById(id);
//		} else {
//			throw new EntityNotFoundException(Proceso.class, "id", id.toString());
//		}
//	}
	
	/**
	 * Método que crea un proceso
	 * @param procesoDto
	 * @return
	 */
	@PostMapping("/new")
	public ResponseEntity<?> crearProceso(@RequestBody ProcesoDto procesoDto){
			Proceso p= procesoServicio.newProceso(procesoDto);	
			return new ResponseEntity<ProcesoDto>(procesoDtoConverter.convertProcesoToProcesoDto(p),HttpStatus.CREATED);
	}

}
