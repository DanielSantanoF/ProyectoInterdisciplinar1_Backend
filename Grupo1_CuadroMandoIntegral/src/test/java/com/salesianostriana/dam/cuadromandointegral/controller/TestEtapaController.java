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
import com.salesianostriana.dam.cuadromandointegral.dto.ColegioDto;
import com.salesianostriana.dam.cuadromandointegral.dto.EtapaDto;
import com.salesianostriana.dam.cuadromandointegral.dto.EtapaDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.dto.NuevaEtapaDto;
import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.Colegio;
import com.salesianostriana.dam.cuadromandointegral.model.CursoAcademico;
import com.salesianostriana.dam.cuadromandointegral.model.Etapa;
import com.salesianostriana.dam.cuadromandointegral.model.Proceso;
import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicador;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;
import com.salesianostriana.dam.cuadromandointegral.servicios.EtapaServicio;


@WebMvcTest(controllers = EtapaController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
public class TestEtapaController {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private EtapaServicio etapaServicio;
	
	@MockBean
	private EtapaDtoConverter etapaDtoConverter;
	
	@MockBean
	private FileStorageService fileStorageService;
	
	@MockBean
	private UserEntityRepositorio userEntityRepositorio;
	
	@MockBean
	private UserDetailsService userDetailsService;
	

	@Test
	@WithMockUser(username="superAdmin", password="1234", roles="SUPER_ADMIN")
	public void whenGetWithData_thenReturns200() throws Exception {
		
		Etapa e = Etapa.builder().colegio(null).cursoAcademico(null)
				.etapa(null).id(1L).nombreSubEtapa(null).build();
		List<Etapa> lista = new ArrayList<>();
		lista.add(e);
		
		EtapaDto etapaDto=EtapaDto.builder().id(1L).colegio(null).nombreSubEtapa("infantil").build();
		List<EtapaDto> listaDto = new ArrayList<>();
		listaDto.add(etapaDto);
		
		when(etapaDtoConverter.convertEtapaToEtapaDto(e)).thenReturn(etapaDto);
		when(etapaServicio.findAll()).thenReturn(lista);
		
		
		mockMvc.perform(get("/etapa/")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
		
	}
	
	@Test
	@WithMockUser(username="superAdmin", password="1234", roles="SUPER_ADMIN")
	public void whenGetByIdCursoAcademico_thenReturns200() throws Exception {
		
		Etapa e = Etapa.builder().colegio(new Colegio()).cursoAcademico(new CursoAcademico(1L,"2020",new ArrayList<Psm>(),new ArrayList<ValorIndicador>(),new ArrayList<Proceso>(),new ArrayList<Etapa>()))
				.etapa(new Etapa()).build();
		List<Etapa> lista = new ArrayList<>();
		lista.add(e);
		
		EtapaDto etapaDto=EtapaDto.builder().id(1L).colegio(new ColegioDto()).nombreSubEtapa("infantil").build();
		List<EtapaDto> listaDto = new ArrayList<>();
		listaDto.add(etapaDto);
		
		when(etapaServicio.findAllByCursoAcademicoId(1L)).thenReturn(lista);
		when(etapaDtoConverter.convertEtapaToEtapaDto(e)).thenReturn(etapaDto);
		
		mockMvc.perform(get("/etapa/1")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
 	@Test
 	@WithMockUser(username="admin",password="1234",roles="ADMIN")

 	public void whenGetWithoutData_thenReturns404() throws Exception {

 		when(etapaServicio.findAll()).thenReturn(Collections.emptyList());
 		mockMvc.perform(get("/etapa/")).andExpect(status().isNoContent());


 	}
	
	
	@Test
	@WithMockUser(username="superAdmin", password="1234", roles="SUPER_ADMIN")
	public void whenPost_thenReturns201() throws Exception {

		NuevaEtapaDto nuevaEtapaDto = NuevaEtapaDto.builder().id(1L).nombreSubEtapa("infantil").colegio(new ColegioDto()).cursoAcademico(1L).build();
		Etapa etapa = Etapa.builder().colegio(new Colegio()).cursoAcademico(new CursoAcademico(1L,"2020",new ArrayList<Psm>(),new ArrayList<ValorIndicador>(),new ArrayList<Proceso>(),new ArrayList<Etapa>()))
				.etapa(new Etapa()).build();
		
		when(etapaDtoConverter.convertEtapaDtoToEtapa(nuevaEtapaDto)).thenReturn(etapa);

		when(etapaServicio.save(etapa)).thenReturn(etapa);

		MvcResult result = mockMvc
				.perform(post("/etapa/newetapa").contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(nuevaEtapaDto)))
				.andExpect(status().isCreated()).andReturn();

		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(etapa)).isEqualToIgnoringCase(resultAsString);

	}
	
	@Test
	@WithMockUser(username="superAdmin", password="1234", roles="SUPER_ADMIN")
	public void whenDelete_thenReturns204() throws Exception {

		Etapa etapa = Etapa.builder().colegio(new Colegio()).cursoAcademico(new CursoAcademico(1L,"2020",new ArrayList<Psm>(),new ArrayList<ValorIndicador>(),new ArrayList<Proceso>(),new ArrayList<Etapa>()))
				.etapa(new Etapa()).build();
		
		when(etapaServicio.findById(1L)).thenReturn(etapa);
		
		mockMvc.perform(delete("/etapa/delete/1")).andExpect(status().isNoContent());
		

	}
	
	
	
	
	
	
	
	
}