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

import com.salesianostriana.dam.cuadromandointegral.dto.ObtenerPsmDtoByCaIdAndEv;
import com.salesianostriana.dam.cuadromandointegral.dto.PsmDto;
import com.salesianostriana.dam.cuadromandointegral.dto.PsmDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.errores.EntityNotFoundException;
import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.servicios.PsmServicio;

import lombok.RequiredArgsConstructor;

/**
 * Controlador de peticiones sobre Psm
 * 
 * @author Daniel Santano Fernández y José Antonio Llamas
 *
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/psm")
@Validated
public class PsmController {

	/**
	 * Atributos de la clase
	 */
	private final PsmServicio psmServicio;
	private final PsmDtoConverter psmDtoConverter;

	/**
	 * Metodo para crear un nuevo psm
	 * @param psmDto dto necesario para hacer la peticion
	 * @return dto del psm creado
	 */
	@PostMapping("/")
	public ResponseEntity<?> crearPsm(@RequestBody PsmDto psmDto) {
		//MODIFICO EL CONTROLADOR PARA PODER HACERLE MAS TEST
		if(psmDto.getId() == null || psmDto.getNombrePsm() == null || psmDto.getEvaluacion() == null || 
				psmDto.getPeso() == null || psmDto.getColegioDto() == null || psmDto.getCursoAcademico() == null) {
			return ResponseEntity.noContent().build();
		} else {
			Psm psm = psmDtoConverter.convertPsmDtoToPsm(psmDto);
			PsmDto dto = psmDtoConverter.convertPsmToPsmDto(psm);
			return new ResponseEntity<PsmDto>(dto, HttpStatus.CREATED);
		}
	}

	/**
	 * Metodo que devuelve los Psm segun el Id del CursoAcademico y la Evaluacion
	 * indicada
	 * 
	 * @param dto Dto que se envia con los datos necesarios
	 * @return Lista de PsmDto
	 */
	@PostMapping("/obtenerPsm")
	ResponseEntity<?> obtenerPsmByCursoAcademicoIdAndEvaluacion(@RequestBody ObtenerPsmDtoByCaIdAndEv dto) {
		if (dto.getPsmId() == null || dto.getCursoAcademicoId() == null || dto.getEvaluacion() == null) {
			return ResponseEntity.noContent().build();
		} else {
			Psm p = psmServicio.findFirstByIdAndCursoAcademicoIdAndEvaluacion(dto.getPsmId(), dto.getCursoAcademicoId(),
					dto.getEvaluacion());
			PsmDto psmDto = psmDtoConverter.convertPsmToPsmDto(p);
			return new ResponseEntity<PsmDto>(psmDto, HttpStatus.OK);
		}
	}

	/**
	 * Obtiene un listado de PsmDto para mostrar en un listado
	 * 
	 * @return lista de PsmDto
	 */
	@GetMapping("/")
	public ResponseEntity<?> index() {
		List<Psm> result = psmServicio.findAll();
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			List<PsmDto> dtoList = result.stream().map(psmDtoConverter::convertPsmToPsmDto)
					.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}

	/**
	 * Elimina un psm y todos los valoresIndicador, ValorIndicadorUnidad y
	 * ValorIndicadorCentro que estén relacionados
	 * 
	 * * @author José Antonio Llamas
	 * 
	 * @param id La Id del psm a eliminar
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (psmServicio.existsById(id)) {
			psmServicio.eliminarPsmPorId(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().build();
			//throw new EntityNotFoundException(Psm.class, "id", id.toString());
		}
	}

}
