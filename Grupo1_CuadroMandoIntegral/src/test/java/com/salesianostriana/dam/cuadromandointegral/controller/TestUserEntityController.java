package com.salesianostriana.dam.cuadromandointegral.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.cuadromandointegral.controladores.UserEntityController;
import com.salesianostriana.dam.cuadromandointegral.dto.UserEntityDto;
import com.salesianostriana.dam.cuadromandointegral.dto.UserEntityDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
import com.salesianostriana.dam.cuadromandointegral.repositorios.ColegioRepositorio;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;
import com.salesianostriana.dam.cuadromandointegral.servicios.UserEntityServicio;

@WebMvcTest(controllers = UserEntityController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
public class TestUserEntityController {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserEntityServicio userEntityServicio;
	
	@MockBean
	private UserEntityDtoConverter userEntityDtoConverter;
	
	@MockBean
	private FileStorageService fileStorageService;
	
	@MockBean
	private UserEntityRepositorio userEntityRepositorio;
	
	@MockBean
	private UserDetailsService userDetailsService;
	
	@MockBean
	private PasswordEncoder passwordEncoder;
	
	@MockBean
	private ColegioRepositorio colegioRepositorio;

	
	@Test
	@WithMockUser(username="superAdmin", password="1234", roles="USER")
	public void whenGetOneUser_thenReturns200() throws Exception {
		
		UserEntity user = UserEntity.builder().colegio(null).createdAt(null).email(null).password("1234").lastPasswordChangedAt(LocalDateTime.now()).id(1L).build();
		UserEntityDto u = UserEntityDto.builder().email("jose@jose").idColegio(1L).nombreColegio("triana").username("jose").role(null).build();
		
		when(userEntityDtoConverter.convertUserEntityToUserEntityDto(user)).thenReturn(u);
		when(userEntityServicio.findFirstById(1L)).thenReturn(user);
		
		
		mockMvc.perform(get("/usuario/1")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
		
	}
	
	@Test
	@WithMockUser(username="superAdmin", password="1234", roles="USER")
	public void whenGetMyUser_thenReturns200() throws Exception {
		
		UserEntity user = UserEntity.builder().colegio(null).createdAt(null).email(null).password("1234").lastPasswordChangedAt(LocalDateTime.now()).id(1L).build();
		Optional<UserEntity> ue = Optional.of(user);
		UserEntityDto u = UserEntityDto.builder().email("jose@jose").idColegio(1L).nombreColegio("triana").username("jose").role(null).build();
		
		when(Optional.of(user)).thenReturn(ue);
		when(userEntityServicio.findFirstByUsername("pedro")).thenReturn(ue);
		when(userEntityDtoConverter.convertUserEntityToUserEntityDto(user)).thenReturn(u);
		
		
		mockMvc.perform(get("/usuario/userData/jose")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
		
	}
}
