package com.salesianostriana.dam.cuadromandointegral.dto;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
import com.salesianostriana.dam.cuadromandointegral.model.enums.Role;
import com.salesianostriana.dam.cuadromandointegral.repositorios.ColegioRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * El DtoConverter de UserEntityDto
 * @author Daniel Santano Fernández 
 *
 */

@RequiredArgsConstructor
@Component
public class UserEntityDtoConverter {

	/**
	 * Lo necesario de la clase
	 */
	private final PasswordEncoder passwordEncoder;
	private final ColegioRepositorio colegioRepositorio;
	
	/**
	 * Metodo para transformar un UserEntity a dto
	 * @param userEntity el usuario
	 * @return el dto del usuario
	 */
	public UserEntityDto convertUserEntityToUserEntityDto(UserEntity userEntity) {
		return UserEntityDto.builder()
				.username(userEntity.getUsername())
				.email(userEntity.getEmail())
				.role(userEntity.getRole())
				.nombreColegio(userEntity.getColegio().getNombreColegio())
				.idColegio(userEntity.getColegio().getId())
				.build();
	}
	
	/**
	 * Metodo para convertir un dto y rol a usuario
	 * @param dto el dto del usuario
	 * @param role el rol del usuario
	 * @return el usuario
	 */
	public UserEntity convertCreateUserEntityDtoToUserEntity(CreateUserEntityDto dto, Role role) {
		return UserEntity.builder()
				.username(dto.getUsername())
				.email(dto.getEmail())
				.password(passwordEncoder.encode(dto.getPassword()))
				.role(Stream.of(role.toString()).collect(Collectors.toSet()))
				.colegio(colegioRepositorio.findFirstById(dto.getIdColegio()))
				.build();
	}
	
}
