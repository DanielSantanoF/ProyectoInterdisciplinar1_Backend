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
import com.salesianostriana.dam.cuadromandointegral.servicios.UserEntityServicio;

import lombok.RequiredArgsConstructor;
/**
 * Controlador para registrarse en el api
 * 
 * @author Daniel Santano Fernández 
 *
 */

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/registrarse")
public class RegistrarseController {

	/*
	 * Lo necesario para el controlador
	 */
	private final UserEntityServicio userEntityServicio;
    private final UserEntityDtoConverter userEntityDtoConverter;
     
    /**
     * Metodo del controlador para registrarte en el api
     * @param username Usuario del usuario
     * @param email Email del usuario
     * @param password Contraseña del usuario
     * @return Respuesta del api
     */
    @PostMapping
    public ResponseEntity<?> registrarse(@RequestBody CreateUserEntityDto createUserEntityDto) {
    	if (createUserEntityDto.getUsername().isEmpty() || createUserEntityDto.getEmail().isEmpty() 
				|| createUserEntityDto.getPassword().isEmpty() || createUserEntityDto.getPassword2().isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
				UserEntity u = userEntityServicio.newUserEntity(createUserEntityDto, createUserEntityDto.getRol(), createUserEntityDto.getIdColegio());
				return new ResponseEntity<UserEntityDto>(userEntityDtoConverter.convertUserEntityToUserEntityDto(u),
						HttpStatus.CREATED);
		}
    }

}
