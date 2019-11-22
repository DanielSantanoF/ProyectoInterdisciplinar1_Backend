package com.salesianostriana.dam.cuadromandointegral.controladores;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cuadromandointegral.dto.UserEntityDto;
import com.salesianostriana.dam.cuadromandointegral.dto.UserEntityDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
import com.salesianostriana.dam.cuadromandointegral.servicios.UserEntityServicio;

import lombok.RequiredArgsConstructor;

/**
 * Cotroller de admin
 * 
 * @author Daniel Santano Fernández
 *
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {

	/**
	 * Atributos necesarios para la clase
	 */
	private final UserEntityServicio userEntityServicio;
	private final UserEntityDtoConverter userEntityDtoConverter;

	/**
	 * Metodo para obtener todos los usuarios de nuestra api
	 * @return List de UserEntityDto con todos los usuarios
	 */
	@GetMapping("/allUsers")
	public ResponseEntity<?> getAllUsuarios() {
		List<UserEntity> lu = userEntityServicio.findAll();
		if (lu.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			List<UserEntityDto> dto= lu.stream().map(userEntityDtoConverter :: convertUserEntityToUserEntityDto)
					.collect(Collectors.toList());
			return new ResponseEntity<List<UserEntityDto>>(dto,HttpStatus.OK);
		}
	}
}
