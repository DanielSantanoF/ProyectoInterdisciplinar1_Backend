package com.salesianostriana.dam.cuadromandointegral.servicios;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cuadromandointegral.dto.CreateUserEntityDto;
import com.salesianostriana.dam.cuadromandointegral.errores.RegisterNewUserException;
import com.salesianostriana.dam.cuadromandointegral.errores.UserNotFoundException;
import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
import com.salesianostriana.dam.cuadromandointegral.model.enums.Role;
import com.salesianostriana.dam.cuadromandointegral.repositorios.ColegioRepositorio;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

/**
 * Servicio para usuario implementando UserDetailsService
 * 
 * @author Daniel Santano Fernández 
 *
 */

@Log
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityServicio implements UserDetailsService{

	/**
	 * Lo necesario para la clase
	 */
	private final PasswordEncoder passwordEncoder;
	private final UserEntityRepositorio userEntityRepositorio;
	private final ColegioRepositorio colegioRepositorio;

	/**
	 * Obtener un usuario por su username
	 * @param username username del usuario
	 * @return el usuario encontrado
	 */
	public UserEntity getUserEntityByUsername(String username)  {
		return this.userEntityRepositorio.findFirstByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
	}

	/**
	 * Metodo para crear un nuevon userEntity
	 * @param createUserEntityDto dto del usuario a crear
	 * @param role rol del usuario
	 * @param idColegio id del colegio del usuario
	 * @return el usuario en UserEntity
	 */
	public UserEntity newUserEntity(CreateUserEntityDto createUserEntityDto, Role role, Long idColegio) {
		
		if (createUserEntityDto.getPassword().isEmpty() || createUserEntityDto.getPassword2().isEmpty())
			throw new RegisterNewUserException("La contraseña no cumple con la política de seguridad");
		
				
		if (!createUserEntityDto.getPassword().equals(createUserEntityDto.getPassword2())) 
			throw new RegisterNewUserException("Las contraseñas no coinciden");
			
		UserEntity newUser = UserEntity.builder()
								.username(createUserEntityDto.getUsername())
								.password(passwordEncoder.encode(createUserEntityDto.getPassword()))
								.email(createUserEntityDto.getEmail())
								.role(Stream.of(role.toString()).collect(Collectors.toSet()))
								.colegio(colegioRepositorio.findFirstById(idColegio))
								.build();
		
		return this.userEntityRepositorio.save(newUser);
	}
	
	/**
	 * Metodo para cargar un usuario por su username
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userEntityRepositorio.findFirstByUsername(username) //userEntityRepositorio.findByEmail(username)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
		log.info(user.toString());
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				Arrays.asList(authority));
	}
	
	/**
	 * Metodo para encontrar un usuario por su id
	 * @param id id del usuario
	 * @return usuario encontrado
	 */
	public UserEntity findFirstById(long id) {
		return userEntityRepositorio.findFirstById(id);
	}
	
	/**
	 * Metodo para buscar un usuario por su username
	 * @param username username del usuario a buscar
	 * @return el usuario encontrado
	 */
	public Optional<UserEntity> findFirstByUsername(String username) {
		return userEntityRepositorio.findFirstByUsername(username);
	}
	
	/**
	 * Metodo para obtener todos los usuarios
	 * @return Lista de usuarios encontrados
	 */
	public List<UserEntity> findAll(){
		return userEntityRepositorio.findAll();
	}

}
