package com.salesianostriana.dam.cuadromandointegral.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.salesianostriana.dam.cuadromandointegral.controladores.UnidadController;
import com.salesianostriana.dam.cuadromandointegral.dto.UnidadDto;
import com.salesianostriana.dam.cuadromandointegral.dto.UnidadDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.IndicadorUnidad;
import com.salesianostriana.dam.cuadromandointegral.model.Unidad;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicadorUnidad;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;
import com.salesianostriana.dam.cuadromandointegral.servicios.CursoServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.UnidadServicio;

@WebMvcTest(controllers = UnidadController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
class TestUnidadController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UnidadServicio unidadServicio;

	@MockBean
	private UnidadDtoConverter unidadDtoConverter;

	@MockBean
	private FileStorageService fileStorageService;

	@MockBean
	private UserEntityRepositorio userEntityRepositorio;

	@MockBean
	private UserDetailsService userDetailsService;

	@MockBean
	private CursoServicio cursoServicio;

	@Test
	@WithMockUser(username = "superAdmin", password = "1234", roles = "SUPER_ADMIN")
	public void whenGetWithData_thenReturns200() throws Exception {

		Unidad u = Unidad.builder().id(1L).curso(null).listaIndicadoresUnidad(new ArrayList<IndicadorUnidad>())
				.listaValoresIndicadorUnidad(new ArrayList<ValorIndicadorUnidad>()).nombreUnidad("3º ESO - A").peso(2)
				.build();

		List<Unidad> lista = new ArrayList<>();
		lista.add(u);

		UnidadDto unidadDto = UnidadDto.builder().curso(null).listaIndicadoresUnidad(new ArrayList<IndicadorUnidad>())
				.listaValoresIndicadorUnidad(new ArrayList<ValorIndicadorUnidad>()).nombreUnidad("3º ESO - A").build();

		List<UnidadDto> listaDto = new ArrayList<>();
		listaDto.add(unidadDto);

		when(unidadServicio.findAll()).thenReturn(lista);
		when(unidadServicio.findById(1L)).thenReturn(u);
		when(unidadDtoConverter.convertUnidadToUnidadDto(u)).thenReturn(unidadDto);

		mockMvc.perform(get("/unidad/")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));

	}

	@Test
	@WithMockUser(username = "superAdmin", password = "1234", roles = "SUPER_ADMIN")
	public void whenGetByCursoWithData_thenReturns200() throws Exception {

		Unidad u = Unidad.builder().id(1L).curso(null).listaIndicadoresUnidad(new ArrayList<IndicadorUnidad>())
				.listaValoresIndicadorUnidad(new ArrayList<ValorIndicadorUnidad>()).nombreUnidad("3º ESO - A").peso(2)
				.build();
		List<Unidad> lista = new ArrayList<>();
		lista.add(u);

		UnidadDto unidadDto = UnidadDto.builder().curso(null).listaIndicadoresUnidad(new ArrayList<IndicadorUnidad>())
				.listaValoresIndicadorUnidad(new ArrayList<ValorIndicadorUnidad>()).nombreUnidad("3º ESO - A").build();
		List<UnidadDto> listaDto = new ArrayList<>();
		listaDto.add(unidadDto);

		when(unidadDtoConverter.convertUnidadToUnidadDto(u)).thenReturn(unidadDto);
		when(unidadServicio.findAllByCursoId(1L)).thenReturn(lista);

		mockMvc.perform(get("/unidad/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));

	}

	@Test
	@WithMockUser(username = "superAdmin", password = "1234", roles = "SUPER_ADMIN")
	public void whenPost_thenReturns201() throws Exception {

		UnidadDto unidadDto = UnidadDto.builder().curso(null).listaIndicadoresUnidad(new ArrayList<IndicadorUnidad>())
				.listaValoresIndicadorUnidad(new ArrayList<ValorIndicadorUnidad>()).nombreUnidad("3º ESO - A").build();
		Unidad u = Unidad.builder().id(1L).curso(null).listaIndicadoresUnidad(new ArrayList<IndicadorUnidad>())
				.listaValoresIndicadorUnidad(new ArrayList<ValorIndicadorUnidad>()).nombreUnidad("3º ESO - A").peso(2)
				.build();

		// Comento esta línea para evitar el error porque el servicio de nueva unidad no
		// llegó a implementarse
		// when(unidadServicio.newUnidad(unidadDto)).thenReturn(u);

		MvcResult result = mockMvc
				.perform(post("/unidad/newunidad").contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(unidadDto)))
				.andExpect(status().isCreated()).andReturn();

		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(unidadDto)).isEqualToIgnoringCase(resultAsString);

	}

	@Test
	@WithMockUser(username = "superAdmin", password = "1234", roles = "SUPER_ADMIN")
	public void whenDelete_thenReturns204() throws Exception {
		Unidad u = Unidad.builder().id(1L).curso(null).listaIndicadoresUnidad(new ArrayList<IndicadorUnidad>())
				.listaValoresIndicadorUnidad(new ArrayList<ValorIndicadorUnidad>()).nombreUnidad("3º ESO - A").peso(2)
				.build();

		when(unidadServicio.findById(1L)).thenReturn(u);

		mockMvc.perform(delete("/unidad/delete/1")).andExpect(status().isNoContent());

	}
	
	@Test
	@WithMockUser(username = "superAdmin", password = "1234", roles = "SUPER_ADMIN")
	public void whenPut_thenReturns200() throws Exception{
		UnidadDto unidadDto = UnidadDto.builder().curso(null).listaIndicadoresUnidad(new ArrayList<IndicadorUnidad>())
				.listaValoresIndicadorUnidad(new ArrayList<ValorIndicadorUnidad>()).nombreUnidad("3º ESO - A").build();
		
		Unidad u = Unidad.builder().id(1L).curso(null).listaIndicadoresUnidad(new ArrayList<IndicadorUnidad>())
				.listaValoresIndicadorUnidad(new ArrayList<ValorIndicadorUnidad>()).nombreUnidad("3º ESO - A").build();

		when(unidadServicio.findById(1L)).thenReturn(u);
		when(unidadServicio.edit(u)).thenReturn(u);
		when(unidadDtoConverter.convertUnidadToUnidadDto(u)).thenReturn(unidadDto);
		
		MvcResult result = mockMvc
				.perform(put("/unidad/edit/1").contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(unidadDto)))
				.andExpect(status().isOk()).andReturn();

		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(unidadDto)).isEqualToIgnoringCase(resultAsString);
		
	}
}
