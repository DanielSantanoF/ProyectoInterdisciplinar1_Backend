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

import com.salesianostriana.dam.cuadromandointegral.controladores.ColegioController;
import com.salesianostriana.dam.cuadromandointegral.dto.ColegioDto;
import com.salesianostriana.dam.cuadromandointegral.dto.ListaColegiosDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
import com.salesianostriana.dam.cuadromandointegral.repositorios.ColegioRepositorio;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;
import com.salesianostriana.dam.cuadromandointegral.servicios.UserEntityServicio;

/**
 * Test de el controlador ColegioController
 * 
 * @author Daniel Santano Fernández
 *
 */

@WebMvcTest(controllers = ColegioController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class ColegioControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ListaColegiosDtoConverter listaColegiosDtoConverter;
	
	@MockBean
	private ColegioRepositorio colegioRepositorio;
	
	@MockBean
	private UserEntity userEntity;
	
	@MockBean
	private UserEntityServicio userEntityServicio;
	
	@MockBean
	private UserEntityRepositorio userEntityRepositorio;
	
	@MockBean
	private FileStorageService fileStorageService;
	
	@MockBean
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Test
	@WithMockUser(username = "usuario", password = "1234", roles = "USER")
	public void whenGetAllColegios_thenReturns200() throws Exception {
		ColegioDto dto = ColegioDto.builder().id(1L).nombre("dto").build();
		List<ColegioDto> ldto = new ArrayList<>();
		ldto.add(dto);
		when(listaColegiosDtoConverter.convertListaColegioToListaColegioDto()).thenReturn(ldto);
		mockMvc.perform(get("/colegio/obtenerColegios")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	@WithMockUser(username = "usuario", password = "1234", roles = "USER")
	public void whenGetAllColegios_thenReturns204() throws Exception {
		when(listaColegiosDtoConverter.convertListaColegioToListaColegioDto()).thenReturn(Collections.emptyList());
		mockMvc.perform(get("/colegio/obtenerColegios")).andExpect(status().isNoContent());
	}
	
}
