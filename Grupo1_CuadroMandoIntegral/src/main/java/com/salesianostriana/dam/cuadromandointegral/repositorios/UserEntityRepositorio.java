package com.salesianostriana.dam.cuadromandointegral.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
/**
 * Repositorio de UserEntity
 * 
 * @author Daniel Santano Fernández Repositorio de UserEntity
 *
 */

public interface UserEntityRepositorio extends JpaRepository<UserEntity, Long> {
	
	/**
	 * Metodo Optional necesario para la seguridad
	 * @param username Atributo username de UserEntity por el que se busca
	 * @return UserEntity encontrado
	 */
	Optional<UserEntity> findFirstByUsername(String username);
	
	/**
	 * Usuario segun id 
	 * @param id id del usuario
	 * @return usuario encontrado
	 */
    UserEntity findFirstById(long id);
	
}
