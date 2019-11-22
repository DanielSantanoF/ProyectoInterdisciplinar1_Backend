package com.salesianostriana.dam.cuadromandointegral.dto;

import com.salesianostriana.dam.cuadromandointegral.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto para crear un usuario
 * 
 * @author Daniel Santano Fernández 
 *
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateUserEntityDto {

	private String username;
	private String password;
	private String password2;
	private String email;
	private Long idColegio;
	private Role rol;
	
}
