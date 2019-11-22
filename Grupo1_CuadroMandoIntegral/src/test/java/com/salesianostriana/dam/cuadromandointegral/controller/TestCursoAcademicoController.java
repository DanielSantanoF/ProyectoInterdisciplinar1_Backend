package com.salesianostriana.dam.cuadromandointegral.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.cuadromandointegral.controladores.CursoAcademicoController;
import com.salesianostriana.dam.cuadromandointegral.dto.CursoAcademicoDto;
import com.salesianostriana.dam.cuadromandointegral.dto.CursoAcademicoDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.CursoAcademico;
import com.salesianostriana.dam.cuadromandointegral.model.Etapa;
import com.salesianostriana.dam.cuadromandointegral.model.Proceso;
import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicador;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;
import com.salesianostriana.dam.cuadromandointegral.servicios.CursoAcademicoServicio;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = CursoAcademicoController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class TestCursoAcademicoController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CursoAcademicoServicio cursoAcademicoServicio;

	@MockBean
	private CursoAcademicoDtoConverter cursoAcademicoDtoConverter;

	@MockBean
	private UserEntity user;

	@MockBean
	private FileStorageService fileStorageService;

	@MockBean
	private UserEntityRepositorio userEntityRepositorio;

	@MockBean
	private UserDetailsService userDetailsService;

	@Test
	@WithMockUser(username = "superAdmin", password = "1234", roles = "SUPER_ADMIN")
	public void whenGetWithDataAndRoleSuperAdmin_thenReturns200() throws Exception {
		CursoAcademico ca = CursoAcademico.builder().id(1L).listaEtapa(new ArrayList<Etapa>()).listaProceso(new ArrayList<Proceso>())
				.listaPsm(new ArrayList<Psm>()).listaValorIndicador(new ArrayList<ValorIndicador>()).nombreCursoAcademico("Curso 2019-2020")
				.build();
		List<CursoAcademico> lista = new ArrayList<>();
		lista.add(ca);

		CursoAcademicoDto cadto = CursoAcademicoDto.builder().id(1L).nombreCursoAcademico("Curso 2019-2020").build();
		List<CursoAcademicoDto> listaDto = new ArrayList<>();
		listaDto.add(cadto);

		when(cursoAcademicoServicio.findAll()).thenReturn(lista);
		when(cursoAcademicoDtoConverter.convertCursoAcademicoToCursoAcademicoDto(ca)).thenReturn(cadto);
		
		mockMvc.perform(get("/cursoacademico/")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	
	@Test
	@WithMockUser(username = "superAdmin", password = "1234", roles = "SUPER_ADMIN")
	public void whenPostWithDataAndRoleSuperAdmin_thenReturns201() throws Exception {

		CursoAcademico ca = CursoAcademico.builder().id(1L).listaEtapa(new ArrayList<Etapa>())
				.listaProceso(new ArrayList<Proceso>()).listaPsm(new ArrayList<Psm>())
				.listaValorIndicador(new ArrayList<ValorIndicador>()).nombreCursoAcademico("Curso 2019-2020").build();

		CursoAcademicoDto cadto = CursoAcademicoDto.builder().id(1L).nombreCursoAcademico("Curso 2019-2020").build();

		when(cursoAcademicoServicio.save(ca)).thenReturn(ca);
		when(cursoAcademicoDtoConverter.cursoAcademicoDtoToCursoAcademico(cadto)).thenReturn(ca);


		MvcResult result = mockMvc.perform(post("/cursoacademico/").contentType("application/json;charset=UTF-8")
				.content(objectMapper.writeValueAsString(cadto))).andExpect(status().isCreated()).andReturn();

		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(ca)).isEqualToIgnoringCase(resultAsString);
	}

	
	@Test
	@WithMockUser(username = "superAdmin", password = "1234", roles = "SUPER_ADMIN")
	public void whenDeleteDataAndRoleSuperAdmin_thenReturns204() throws Exception {

		CursoAcademico ca = CursoAcademico.builder().id(1L).listaEtapa(new ArrayList<Etapa>())
				.listaProceso(new ArrayList<Proceso>()).listaPsm(new ArrayList<Psm>())
				.listaValorIndicador(new ArrayList<ValorIndicador>()).nombreCursoAcademico("Curso 2019-2020").build();
		
		when(cursoAcademicoServicio.existsById(1L)).thenReturn(true);
//		when(cursoAcademicoServicio.findFirstById(1L).getListaPsm().isEmpty()).thenReturn(false);
//		when(cursoAcademicoServicio.findFirstById(1L).getListaEtapa().isEmpty()).thenReturn(false);
//		when(cursoAcademicoServicio.findFirstById(1L).getListaValorIndicador().isEmpty()).thenReturn(false);
//		when(cursoAcademicoServicio.findFirstById(1L).getListaProceso().isEmpty()).thenReturn(false);

//		when(!cursoAcademicoServicio.findFirstById(1L).getListaPsm().isEmpty()
//					|| !cursoAcademicoServicio.findFirstById(1L).getListaEtapa().isEmpty()
//					|| !cursoAcademicoServicio.findFirstById(1L).getListaValorIndicador().isEmpty()
//					|| !cursoAcademicoServicio.findFirstById(1L).getListaProceso().isEmpty()).thenReturn(false, false,false,false);
		
		mockMvc.perform(delete("/cursoacademico/delete/1")).andExpect(status().isNoContent());
	}

}
