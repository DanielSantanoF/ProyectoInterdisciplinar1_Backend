package com.salesianostriana.dam.cuadromandointegral.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.salesianostriana.dam.cuadromandointegral.controladores.IndicadorController;
import com.salesianostriana.dam.cuadromandointegral.dto.CursoAcademicoDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.dto.IndicadorDto;
import com.salesianostriana.dam.cuadromandointegral.dto.IndicadorDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.dto.NuevoIndicadorDto;
import com.salesianostriana.dam.cuadromandointegral.dto.ProcesoDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.Indicador;
import com.salesianostriana.dam.cuadromandointegral.repositorios.IndicadorRepositorio;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;
import com.salesianostriana.dam.cuadromandointegral.servicios.IndicadorServicio;

@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = IndicadorController.class)
public class TestIndicadorController {

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
	private IndicadorServicio indicadorServicio;

	@MockBean

	private IndicadorRepositorio indicadorRepositorio;

	@MockBean

	private IndicadorDtoConverter indicadorConverter;

	@MockBean

	private ProcesoDtoConverter procesoConverter;

	@MockBean

	private CursoAcademicoDtoConverter cursoAcademicoConverter;
	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenGetAll_thenReturns200() throws Exception {
		Indicador i = new Indicador(1L, "adfs", false, null);
		List<Indicador> indicadores = new ArrayList<>();
		indicadores.add(i);
		List<IndicadorDto> indicadoresdto = new ArrayList<>();
		IndicadorDto idto = IndicadorDto.builder().id(1L).nombreIndicador("dafs").esPorcentaje(true).proceso(null).build();
		indicadoresdto.add(idto);
		when(indicadorServicio.findAll()).thenReturn(indicadores);
		when(indicadorConverter.convertIndicadorToIndicadorDto(i)).thenReturn(idto);
		when(indicadorServicio.findById(1L)).thenReturn(i);
		
 		
 			mockMvc.perform(get("/indicador/indicadores")).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
		
	}
	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenGetWithoutData_thenReturns204() throws Exception {
		when(indicadorServicio.findAll()).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/indicador/indicadores")).andExpect(status().isNoContent());

	}
	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenGetById_thenReturns200() throws Exception {
		Indicador i = new Indicador(1L, "adfs", false, null);
		List<Indicador> indicadores = new ArrayList<>();
		indicadores.add(i);
		List<IndicadorDto> indicadoresdto = new ArrayList<>();
		IndicadorDto idto = IndicadorDto.builder().id(1L).nombreIndicador("dafs").esPorcentaje(true).proceso(null).build();
		indicadoresdto.add(idto);
		when(indicadorServicio.findByProcesoId(1L)).thenReturn(indicadores);
		when(indicadorConverter.convertIndicadorToIndicadorDto(i)).thenReturn(idto);
		when(indicadorServicio.findById(1L)).thenReturn(i);
		
 		
 			mockMvc.perform(get("/indicador/1")).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
		
	}
	
	
	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenDeleteById_thenReturns200() throws Exception {
		Indicador i = new Indicador(1L, "adfs", false, null);
		when(indicadorServicio.findById(1L)).thenReturn(i);
		
		indicadorServicio.deleteById(i.getId());

		mockMvc.perform(delete("/indicador/delete/1"))
			.andExpect(status().isNoContent())
			.andExpect(jsonPath("$.id").doesNotExist());

	}

	
	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenEdit_thenReturns200() throws Exception {
		IndicadorDto idto = IndicadorDto.builder().id(1L).nombreIndicador("dafs").esPorcentaje(true).proceso(null).build();
		Indicador i = new Indicador(1L, "adfs", false, null);
		when(indicadorServicio.findById(1L)).thenReturn(i);
		when(indicadorServicio.edit(i)).thenReturn(i);
		when(indicadorConverter.convertIndicadorToIndicadorDto(i)).thenReturn(idto);

		mockMvc.perform(put("/indicador/edit/1").contentType("application/json").content(objectMapper.writeValueAsString(i)))
			.andExpect(status().isOk());
			

	}
	
	
	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenPost_thenReturns201() throws Exception {
		NuevoIndicadorDto preSaved =  NuevoIndicadorDto.builder().id(1L).esPorcentaje(true).nombreIndicador("adfsd").build();
		Indicador i = new Indicador(1L, "adfs", false, null);
		when(indicadorServicio.save(i)).thenReturn(i);
		when(indicadorRepositorio.save(i)).thenReturn(i);
		when(indicadorConverter.convertIndicadorDtoToNuevoIndicador(preSaved)).thenReturn(i);
 		
		
		mockMvc.perform(post("/indicador/newindicador").contentType("application/json")
						.content(objectMapper.writeValueAsString(preSaved)))
				.andExpect(status().isCreated()).andReturn();

	}

	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenPost_thenReturns204() throws Exception {
		NuevoIndicadorDto preSaved =  NuevoIndicadorDto.builder().id(1L).esPorcentaje(true).nombreIndicador("").build();
		Indicador i = new Indicador(1L, "adfs", false, null);
		when(indicadorServicio.save(i)).thenReturn(i);
		when(indicadorRepositorio.save(i)).thenReturn(i);
		when(indicadorConverter.convertIndicadorDtoToNuevoIndicador(preSaved)).thenReturn(i);
 		
		
		mockMvc.perform(post("/indicador/newindicador").contentType("application/json")
						.content(objectMapper.writeValueAsString(preSaved)))
				.andExpect(status().isNoContent()).andReturn();

	}

	

}
