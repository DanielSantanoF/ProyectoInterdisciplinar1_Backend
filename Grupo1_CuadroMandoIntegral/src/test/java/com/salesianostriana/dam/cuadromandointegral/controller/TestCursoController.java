package com.salesianostriana.dam.cuadromandointegral.controller;

import static org.assertj.core.api.Assertions.assertThat;
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
import java.util.Optional;

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
import com.salesianostriana.dam.cuadromandointegral.controladores.CursoController;
import com.salesianostriana.dam.cuadromandointegral.dto.CursoDto;
import com.salesianostriana.dam.cuadromandointegral.dto.CursoDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.dto.UnidadDto;
import com.salesianostriana.dam.cuadromandointegral.dto.UnidadDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.Curso;
import com.salesianostriana.dam.cuadromandointegral.model.Etapa;
import com.salesianostriana.dam.cuadromandointegral.model.Unidad;
import com.salesianostriana.dam.cuadromandointegral.repositorios.CursoRepositorio;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;
import com.salesianostriana.dam.cuadromandointegral.servicios.CursoServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.EtapaServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.UnidadServicio;


@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = CursoController.class)
class TestCursoController {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CursoServicio cursoServicio;
	@MockBean
	private CursoRepositorio cursoRepositorio;
	
	
	@MockBean
	private CursoDtoConverter cursoConverter;
	
	@MockBean
	private UnidadDtoConverter unidadConverter;
	@MockBean
	private UnidadServicio unidadServicio;
	
	@MockBean
	private EtapaServicio etapaServicio;
	@MockBean
 	private UserEntityRepositorio userEntityRepositorio;
 
 	@MockBean
 	private UserDetailsService userDetailsService;

 	@MockBean
 	private FileStorageService fileStorageService;
	
	
	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenGetById_thenReturns200() throws Exception {
		
		List<Unidad> unidades = new ArrayList<>();
		Unidad u = Unidad.builder().id(1L).nombreUnidad("unidad").listaIndicadoresUnidad(null).listaValoresIndicadorUnidad(null).build();
		unidades.add(u);
		Curso c = Curso.builder().id(1L).nombreCurso("2º DAM").peso(1).unidades(unidades).build();
		List<Curso> cursos = new ArrayList<>();
		cursos.add(c);
		List<UnidadDto> unidadesDto = new ArrayList<>();
		UnidadDto udto = UnidadDto.builder().id(2L).nombreUnidad("unidad").listaIndicadoresUnidad(null).listaValoresIndicadorUnidad(null).build();
		unidadesDto.add(udto);
		CursoDto cdto = CursoDto.builder().id(3L).nombreCurso("2º DAM").nombreEtapa("").peso(1).unidades(unidadesDto).build();
		List<CursoDto> cursosDto = new ArrayList<>();
		

 		when(cursoServicio.fiNdAllByEtapaId(1L)).thenReturn(cursos);
 		when(cursoConverter.convertCursoToCursoDto(c)).thenReturn(cdto);
 		when(unidadConverter.convertUnidadToUnidadDto(u)).thenReturn(udto);
 			mockMvc.perform(get("/cursos/2")).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
		
	}
	

	
	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenPost_thenReturns201() throws Exception {
		Unidad u = Unidad.builder().id(1L).nombreUnidad("unidad").listaIndicadoresUnidad(null).listaValoresIndicadorUnidad(null).build();
		Etapa e = Etapa.builder().id(1L).colegio(null).nombreSubEtapa("").build();
		CursoDto preSaved =  CursoDto.builder().id(1L).nombreCurso("2º DAM").peso(1).unidades(null).build();
		Curso postSaved = Curso.builder().id(1L).nombreCurso("2º DAM").peso(1).unidades(null).build();
	
		when(cursoConverter.convertCursoToCursoDto(postSaved)).thenReturn(preSaved);
		when(cursoServicio.newCurso(preSaved)).thenReturn(postSaved);
		when(unidadServicio.findById(1L)).thenReturn(u);
		when(etapaServicio.findByNombreSubEtapa("")).thenReturn(e);
		when(cursoRepositorio.save(postSaved)).thenReturn(postSaved);
		mockMvc
				.perform(post("/cursos/new").contentType("application/json")
						.content(objectMapper.writeValueAsString(preSaved)))
				.andExpect(status().isCreated()).andReturn();

	


}
	@Test
	@WithMockUser(username="admin",password="1234",roles="ADMIN")
	public void whenDeleteById_thenReturns204() throws Exception {
		
		
		Curso c = Curso.builder().id(1L).nombreCurso("2º DAM").peso(1).unidades(null).build();
		when( cursoServicio.findById(1l)).thenReturn(c);
		
		cursoServicio.deleteById(c.getId());

		mockMvc.perform(delete("/cursos/delete/1"))
			.andExpect(status().isNoContent())
			.andExpect(jsonPath("$.id").doesNotExist());

	}
}
