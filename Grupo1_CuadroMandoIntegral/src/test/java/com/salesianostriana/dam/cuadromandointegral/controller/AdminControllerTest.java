package com.salesianostriana.dam.cuadromandointegral.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.salesianostriana.dam.cuadromandointegral.controladores.AdminController;
import com.salesianostriana.dam.cuadromandointegral.dto.UserEntityDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;
import com.salesianostriana.dam.cuadromandointegral.servicios.UserEntityServicio;

/**
 * Test de el controlador AdminController
 * 
 * @author Daniel Santano Fernández
 *
 */

@WebMvcTest(controllers = AdminController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserEntityServicio userEntityServicio;
	
	@MockBean
	private UserEntityDtoConverter userEntityDtoConverter;
	
	@MockBean
	private UserEntity userEntity;
	
	@MockBean
	private FileStorageService fileStorageService;
	
	@MockBean
	private UserEntityRepositorio userEntityRepositorio;
	
	@MockBean
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	@WithMockUser(username = "usuario", password = "1234", roles = "USER")
	public void whenGetAllUsuarios_thenReturns200() throws Exception {
		UserEntity u = new UserEntity(1L, "usuarioTest", "uTest@uTest.com", "1234", null, null, null, null);
		List<UserEntity> lu = new ArrayList<>();
		lu.add(u);
		when(userEntityServicio.findAll()).thenReturn(lu);
		mockMvc.perform(get("/admin/allUsers")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	@WithMockUser(username = "usuario", password = "1234", roles = "USER")
	public void whenGetAllUsuarios_thenReturns204() throws Exception {
		when(userEntityServicio.findAll()).thenReturn(Collections.emptyList());
		mockMvc.perform(get("/admin/allUsers")).andExpect(status().isNoContent());
	}
	
}
