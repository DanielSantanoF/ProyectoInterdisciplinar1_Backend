package com.salesianostriana.dam.cuadromandointegral.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.cuadromandointegral.controladores.PuntoControlController;
import com.salesianostriana.dam.cuadromandointegral.dto.ColegioDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.dto.PsmDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.dto.PuntoControlDto;
import com.salesianostriana.dam.cuadromandointegral.dto.PuntoControlDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.PuntoControl;
import com.salesianostriana.dam.cuadromandointegral.repositorios.PuntoControlRepositorio;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;
import com.salesianostriana.dam.cuadromandointegral.servicios.ColegioServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.PsmServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.PuntoControlServicio;

@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = PuntoControlController.class)
public class TestPuntoControlController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private UserEntityRepositorio userEntityRepositorio;

	@MockBean
	private UserDetailsService userDetailsService;

	@MockBean
	private FileStorageService fileStorageService;

	@MockBean
	private PuntoControlServicio puntoControlServicio;
	@MockBean
	private PuntoControlRepositorio puntoControlRespositorio;

	@MockBean
	private PuntoControlDtoConverter puntoControlConverter;
	@MockBean
	private ColegioDtoConverter colegioConverter;
	@MockBean
	private ColegioServicio colegioServicio;
	@MockBean
	private PsmDtoConverter psmConverter;
	@MockBean
	private PsmServicio psmServicio;
	
	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenGetById_thenReturns200() throws Exception {
		
		PuntoControl pc = PuntoControl.builder().id(1L).colegio(null).listaPsm(null).fecha(null).build();
		List<PuntoControl> puntos = new ArrayList<>();
		puntos.add(pc);
		List<PuntoControlDto> puntosDto = new ArrayList<>();
		PuntoControlDto pcdto = PuntoControlDto.builder().id(1L).fecha(null).colegioDto(null).psm(null).build();
		puntosDto.add(pcdto);
		when(puntoControlServicio.findAllByCursoAcademicoId(1L)).thenReturn(puntos);
		when(psmServicio.findAllByCursoAcademicoId(1L)).thenReturn(null);
 		when(puntoControlConverter.convertPuntoControlToPuntoControlDto(pc)).thenReturn(pcdto);
 		when(colegioConverter.convertColegioToColegioDto(null)).thenReturn(null);
 		when(psmConverter.convertListPsmToListPsmDto(null)).thenReturn(null);
 		
 			mockMvc.perform(get("/puntosControl/2")).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
		
	}
	@Test
	@WithMockUser(username="superAdmin",password="1234",roles="SUPER_ADMIN")
	public void whenGetVacios_thenReturns200() throws Exception {
		List<Psm> psm = new ArrayList<>();
		PuntoControl pc = PuntoControl.builder().id(1L).colegio(null).listaPsm(psm).fecha(null).build();
		List<PuntoControl> puntos = new ArrayList<>();
		puntos.add(pc);
		List<PuntoControlDto> puntosDto = new ArrayList<>();
		PuntoControlDto pcdto = PuntoControlDto.builder().id(1L).fecha(null).colegioDto(null).psm(null).build();
		puntosDto.add(pcdto);
		when(puntoControlServicio.findAll()).thenReturn(puntos);
 		when(puntoControlConverter.convertPuntoControlVacioToPuntoControlDto(pc)).thenReturn(pcdto);
 		when(colegioConverter.convertColegioToColegioDto(null)).thenReturn(null);

 		
 			mockMvc.perform(get("/puntosControl/puntos/vacios")).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
		
	}
	
	@Test
	@WithMockUser(username="superAdmin",password="1234",roles="SUPER_ADMIN")
	public void whenGetVacios_thenReturns204() throws Exception {
		
		when(puntoControlServicio.findAll()).thenReturn(Collections.emptyList());
 	
		mockMvc.perform(get("/puntosControl/puntos/vacios")).andExpect(status().isNoContent());
		
	}
	
	
	
	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenDeleteById_thenReturns200() throws Exception {

		
		PuntoControl c = PuntoControl.builder().id(1L).colegio(null).listaPsm(null).fecha(null).build();
		when( puntoControlServicio.existsById(1l)).thenReturn(true);
		
		puntoControlServicio.deleteById(c.getId());

		mockMvc.perform(delete("/puntosControl/delete/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").doesNotExist());

	}
	
	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenPost_thenReturns201() throws Exception {
		PuntoControlDto preSaved =  PuntoControlDto.builder().id(1L).colegioDto(null).fecha(null).psm(null).fecha(null).build();
		PuntoControl postSaved = PuntoControl.builder().id(1L).colegio(null).fecha(null).listaPsm(null).build();
		when(puntoControlServicio.newPuntoControl(preSaved)).thenReturn(postSaved);
		when(colegioServicio.findById(1L)).thenReturn(null);
		when(puntoControlConverter.convertPuntoControlToPuntoControlDto(postSaved)).thenReturn(preSaved);
 		when(colegioConverter.convertColegioToColegioDto(null)).thenReturn(null);
 		when(psmConverter.convertListPsmToListPsmDto(null)).thenReturn(null);
 		when(puntoControlRespositorio.save(postSaved)).thenReturn(postSaved);
 		
		
		mockMvc
				.perform(post("/puntosControl/new").contentType("application/json")
						.content(objectMapper.writeValueAsString(preSaved)))
				.andExpect(status().isCreated()).andReturn();

	}

	
	
	

}
