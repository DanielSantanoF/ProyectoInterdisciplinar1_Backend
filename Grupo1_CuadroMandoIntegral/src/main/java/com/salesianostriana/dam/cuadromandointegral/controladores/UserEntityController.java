package com.salesianostriana.dam.cuadromandointegral.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cuadromandointegral.dto.UserEntityDto;
import com.salesianostriana.dam.cuadromandointegral.dto.UserEntityDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
import com.salesianostriana.dam.cuadromandointegral.servicios.UserEntityServicio;

import lombok.RequiredArgsConstructor;

/**
 * Controlador de UserEntity para manejo de usuarios
 * 
 * @author Daniel Santano Fernández 
 *
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
@Validated
public class UserEntityController {

	/**
	 * Lo necesario para el controlador
	 */

	private final UserEntityServicio userEntityServicio;
	private final UserEntityDtoConverter userEntityDtoConverter;
	
	/**
	 * Metodo para obtener un usuario por su ID
	 * @param id Id del usuario que queremos recibir la informacion
	 * @return Respuesta del api
	 */
	@GetMapping("/{id}")
	@PostAuthorize("!hasAuthority('USER')")
	ResponseEntity<?> getOneUser(@PathVariable Long id) {
		if(id == null) {
			return ResponseEntity.noContent().build();
		}else {
			UserEntityDto u = userEntityDtoConverter.convertUserEntityToUserEntityDto(userEntityServicio.findFirstById(id));
		return new ResponseEntity<UserEntityDto>(u,HttpStatus.OK);
		}
	}
	
	/**
	 * Metodo para obtener un usuario por username
	 * @param username Atributo username de UserEntity
	 * @return UserEntityDto con los datos del usuario
	 */
	@GetMapping("/userData/{username}")
	@PostAuthorize("!hasAuthority('USER')")
	ResponseEntity<?> getMyUser(@PathVariable String username) {
		if(username.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			Optional<UserEntity> uOpt = userEntityServicio.findFirstByUsername(username);
			if (uOpt.isPresent()) {
				UserEntity u = uOpt.get();
				UserEntityDto dto = userEntityDtoConverter.convertUserEntityToUserEntityDto(u);
				return new ResponseEntity<UserEntityDto>(dto,HttpStatus.OK);
			} else {
				return ResponseEntity.noContent().build();
			}
		}
	}
	
}
