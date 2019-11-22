package com.salesianostriana.dam.cuadromandointegral.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto de UserEntity
 * 
 * @author Daniel Santano Fernández 
 *
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserEntityDto {

	private String username;
	private String email;
	private Set<String> role;
	private String nombreColegio;
	private Long idColegio;
	
}
