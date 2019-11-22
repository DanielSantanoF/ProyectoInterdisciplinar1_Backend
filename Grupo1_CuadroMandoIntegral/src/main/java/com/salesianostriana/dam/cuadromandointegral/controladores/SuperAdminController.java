package com.salesianostriana.dam.cuadromandointegral.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cuadromandointegral.dto.CreateUserEntityDto;
import com.salesianostriana.dam.cuadromandointegral.dto.UserEntityDto;
import com.salesianostriana.dam.cuadromandointegral.dto.UserEntityDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
import com.salesianostriana.dam.cuadromandointegral.model.enums.Role;
import com.salesianostriana.dam.cuadromandointegral.servicios.UserEntityServicio;

import lombok.RequiredArgsConstructor;

/**
 * Controlador de superAdmin para crear nuevo admin
 * 
 * @author Daniel Santano Fernández y Jose Luis Diéz
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/superAdmin")
@Validated
public class SuperAdminController {

	/**
	 * Atributos de la clase
	 */
	private final UserEntityServicio userEntityServicio;
	private final UserEntityDtoConverter userEntityDtoConverter;

	/**
	 * Metodo para crear un nuevo usuario que es admin
	 * @param createUserEntityDto dto con toda la informacion necesaria para procesar la peticion
	 * @return dto con la informacion del usuario creado
	 */
	@PostMapping
	public ResponseEntity<?> crearNuevoAdmin(@RequestBody CreateUserEntityDto createUserEntityDto) {
		if (createUserEntityDto.getUsername().isEmpty() || createUserEntityDto.getEmail().isEmpty()
				|| createUserEntityDto.getPassword().isEmpty() || createUserEntityDto.getPassword2().isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			UserEntity u = userEntityServicio.newUserEntity(createUserEntityDto, Role.ADMIN,
					createUserEntityDto.getIdColegio());
			return new ResponseEntity<UserEntityDto>(userEntityDtoConverter.convertUserEntityToUserEntityDto(u),
					HttpStatus.CREATED);
		}
	}


}
