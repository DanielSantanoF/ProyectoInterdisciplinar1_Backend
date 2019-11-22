package com.salesianostriana.dam.cuadromandointegral.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.cuadromandointegral.controladores.SuperAdminController;
import com.salesianostriana.dam.cuadromandointegral.dto.CreateUserEntityDto;
import com.salesianostriana.dam.cuadromandointegral.dto.UserEntityDto;
import com.salesianostriana.dam.cuadromandointegral.dto.UserEntityDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.Colegio;
import com.salesianostriana.dam.cuadromandointegral.model.Etapa;
import com.salesianostriana.dam.cuadromandointegral.model.IndicadorCentro;
import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.PuntoControl;
import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicadorCentro;
import com.salesianostriana.dam.cuadromandointegral.model.enums.Role;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;
import com.salesianostriana.dam.cuadromandointegral.servicios.ColegioServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.UserEntityServicio;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = SuperAdminController.class)
@MockBean(JpaMetamodelMappingContext.class)
class TestSuperAdminController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UserEntityServicio userEntityServicio;

	@MockBean
	private UserEntityDtoConverter userEntityDtoConverter;

	@MockBean
	private UserEntity user;
	
	@MockBean
	private FileStorageService fileStorageService;

	@MockBean
	private UserEntityRepositorio userEntityRepositorio;
	
	@MockBean
	private ColegioServicio colegioServicio;
	
	@MockBean
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	@WithMockUser(username = "superAdmin", password = "1234", roles = "SUPER_ADMIN")
	public void whenPostWithDataAndRoleSuperAdmin_thenReturns201() throws Exception {
		Colegio colegio = Colegio.builder().id(1L).etapas(new ArrayList<Etapa>()).nombreColegio("Salesianos")
				.listaIndicadorCentro(new ArrayList<IndicadorCentro>()).listaPsm(new ArrayList<Psm>())
				.listaPuntosDeControl(new ArrayList<PuntoControl>()).listaUsuarios(new ArrayList<UserEntity>())
				.listaValoresIndicadorCentro(new ArrayList<ValorIndicadorCentro>()).build();
		
		CreateUserEntityDto createUserDto = CreateUserEntityDto.builder().username("Usuario1").password("1234")
				.password2("1234").email("usuario1@usuario.com").idColegio(1L).rol(Role.ADMIN).build();

		Set<String> roles=new HashSet<String>();
		roles.add("ADMIN");
		
		UserEntity user = UserEntity.builder().username("usuario1").password("1234").email("usuario1@usuario.com")
				.colegio(colegio).role(roles).build();
		
		UserEntityDto userDto=UserEntityDto.builder().username("usuario1").email("usuario1@usuario.com").idColegio(1L).nombreColegio("Salesianos")
				.role(roles).build();
		
		when(userEntityServicio.newUserEntity(createUserDto, Role.ADMIN,createUserDto.getIdColegio())).thenReturn(user);
		when(userEntityDtoConverter.convertUserEntityToUserEntityDto(user)).thenReturn(userDto);
		
			
		MvcResult result = mockMvc
								.perform(post("/superAdmin").contentType("application/json;charset=UTF-8")
										.content(objectMapper.writeValueAsString(createUserDto)))
								.andExpect(status().isCreated()).andReturn();

		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(user))
		.isEqualToIgnoringCase(resultAsString);
	}

}
