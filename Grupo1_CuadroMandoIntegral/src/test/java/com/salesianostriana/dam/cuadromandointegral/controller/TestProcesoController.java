package com.salesianostriana.dam.cuadromandointegral.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.cuadromandointegral.controladores.EtapaController;
import com.salesianostriana.dam.cuadromandointegral.controladores.ProcesoController;
import com.salesianostriana.dam.cuadromandointegral.dto.ColegioDto;
import com.salesianostriana.dam.cuadromandointegral.dto.CursoAcademicoDto;
import com.salesianostriana.dam.cuadromandointegral.dto.EtapaDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.dto.NuevaEtapaDto;
import com.salesianostriana.dam.cuadromandointegral.dto.ProcesoDto;
import com.salesianostriana.dam.cuadromandointegral.dto.ProcesoDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.Colegio;
import com.salesianostriana.dam.cuadromandointegral.model.CursoAcademico;
import com.salesianostriana.dam.cuadromandointegral.model.Etapa;
import com.salesianostriana.dam.cuadromandointegral.model.Proceso;
import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicador;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;
import com.salesianostriana.dam.cuadromandointegral.servicios.CursoAcademicoServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.EtapaServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.ProcesoServicio;

@WebMvcTest(controllers = ProcesoController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
public class TestProcesoController {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ProcesoServicio procesoServicio;
	
	@MockBean
	private ProcesoDtoConverter procesoDtoConverter;
	
	@MockBean
	private FileStorageService fileStorageService;
	
	@MockBean
	private UserEntityRepositorio userEntityRepositorio;
	
	@MockBean
	private UserDetailsService userDetailsService;
	
	@MockBean
	private CursoAcademicoServicio cursoAcademicoServicio;
	
	
	
	@Test
	@WithMockUser(username="superAdmin", password="1234", roles="SUPER_ADMIN")
	public void whenMostrarProcesos_thenReturns200() throws Exception {
		
		Proceso p = Proceso.builder().cursoAcademico(new CursoAcademico(1L,"2020",new ArrayList<Psm>(),new ArrayList<ValorIndicador>(),new ArrayList<Proceso>(),new ArrayList<Etapa>())).id(1L).nombreProceso("jose").peso(10).tipo("clave").build();
		List<Proceso> lista = new ArrayList<>();
		lista.add(p);
		
		ProcesoDto procesoDto=ProcesoDto.builder().cursoAcademico(null).nombre(null).tipo(null).build();
		List<ProcesoDto> listaDto = new ArrayList<>();
		listaDto.add(procesoDto);
		
		when(procesoDtoConverter.convertProcesoToProcesoDto(p)).thenReturn(procesoDto);
		when(procesoServicio.findAllByCursoAcademicoId(1L)).thenReturn(lista);
		
		
		mockMvc.perform(get("/procesos/1")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
		
	}
	
	@Test
	@WithMockUser(username="superAdmin", password="1234", roles="SUPER_ADMIN")
	public void whenPost_thenReturns201() throws Exception {
		
		CursoAcademicoDto cad = new CursoAcademicoDto(1L,"2020");
		ProcesoDto procesoDto=ProcesoDto.builder().cursoAcademico(cad).id(1L).nombre(null).tipo(null).build();
		Proceso p = Proceso.builder().cursoAcademico(null).id(1L).nombreProceso(null).peso(null).tipo(null).build();
		CursoAcademico ca = CursoAcademico.builder().id(1L).listaEtapa(null).listaProceso(null).listaPsm(null).listaValorIndicador(null).nombreCursoAcademico("Curso 2019-2020").build();
		
		when(procesoServicio.newProceso(procesoDto)).thenReturn(p);
		when(cursoAcademicoServicio.findById(1L)).thenReturn(ca);

		MvcResult result = mockMvc
				.perform(post("/procesos/new").contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(procesoDto)))
				.andExpect(status().isCreated()).andReturn();

		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(procesoDto)).isEqualToIgnoringCase(resultAsString);

	}
	//modificamos el test de delete
	@Test
	@WithMockUser(username="superAdmin", password="1234", roles="SUPER_ADMIN")
	public void whenDelete_thenReturns204() throws Exception {

		Proceso p = Proceso.builder().cursoAcademico(null).id(1L).nombreProceso(null).peso(null).tipo(null).build();
		
		when(procesoServicio.findById(1L)).thenReturn(p);
		
		mockMvc.perform(delete("/procesos/delete/1")).andExpect(status().isNoContent());
		

	}
}
