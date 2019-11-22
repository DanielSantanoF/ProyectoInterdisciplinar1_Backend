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

import com.salesianostriana.dam.cuadromandointegral.dto.EtapaDto;
import com.salesianostriana.dam.cuadromandointegral.dto.EtapaDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.dto.NuevaEtapaDto;
import com.salesianostriana.dam.cuadromandointegral.errores.EntityNotFoundException;
import com.salesianostriana.dam.cuadromandointegral.model.Etapa;
import com.salesianostriana.dam.cuadromandointegral.servicios.EtapaServicio;

import lombok.RequiredArgsConstructor;
/**
 * 
 * @author jldiez Controlador de peticiones sobre Etapas
 *
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/etapa")
public class EtapaController {

	/**
	 * lo necesario para la clase
	 */
	private final EtapaServicio etapaServicio;
	private final EtapaDtoConverter etapaDtoConverter;
	
	/**
	 * Controlador que devuelve una lista de todas las etapas
	 * @return Lista de Dto de Etapa
	 */
	@GetMapping("/")
	public ResponseEntity<?>mostrarEtapas(){
		List<Etapa> lista = etapaServicio.findAll();
		if (lista.isEmpty())
			return ResponseEntity.noContent().build();
		else {
			List<EtapaDto> listaDto = new ArrayList<>();
			for (Etapa cursol2 : lista) {
				listaDto.add(etapaDtoConverter.convertEtapaToEtapaDto(etapaServicio.findById(cursol2.getId())));
			}
			return new ResponseEntity<List<EtapaDto>>(listaDto, HttpStatus.OK);
		}
	}
	/**
	 * Controlador que devuelve una lista de etapas  que pertenecen
	 * a un curso academico
	 * @param id Id del Curso académico 
	 * @return Lista de Dto de Etapa
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> mostarEtapasCursoAcademico(@PathVariable Long id) {
		List<Etapa> lista = etapaServicio.findAllByCursoAcademicoId(id);
		if (id == null) {
			return ResponseEntity.noContent().build();
		} else {
			List<EtapaDto> listaDto = new ArrayList<>();
			for (Etapa etapal2 : lista) {
				listaDto.add(etapaDtoConverter.convertEtapaToEtapaDto(etapaServicio.findById(etapal2.getId())));
			}
			return new ResponseEntity<List<EtapaDto>>(listaDto, HttpStatus.OK);
		}
	}
	
	/**
	 * Controlador para crear una nueva etapa
	 * @param Dto de una nueva etapa
	 * @return Lista de Dto de Etapa
	 */
	@PostMapping("/newetapa")
	// @PreAuthorize("hasAuthority('SUPERADMIN')")
	public ResponseEntity<?> nuevoEtapa(@RequestBody NuevaEtapaDto etapaDto) {
		Etapa e = etapaDtoConverter.convertEtapaDtoToEtapa(etapaDto);
		etapaServicio.save(e);
		return new ResponseEntity<NuevaEtapaDto>(etapaDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Controlador para borrar una  etapa por su id
	 * @param id Id de la etapa que queremos borrar 
	 * @return ResponseEntity sin contenido
	 */
	@DeleteMapping("/delete/{id}")
	// @PostAuthorize("!hasAuthority('SUPER_ADMIN')")
	public ResponseEntity<?> deleteEtapa(@PathVariable Long id) {
		Etapa i = etapaServicio.findById(id);
		if (i == null) {
			throw new EntityNotFoundException(Etapa.class, "id", id.toString());
		}else {
			etapaServicio.deleteById(id);
			return ResponseEntity.noContent().build();
		}
	}
}
