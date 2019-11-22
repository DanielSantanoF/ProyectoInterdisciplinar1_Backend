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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.cuadromandointegral.controladores.PsmController;
import com.salesianostriana.dam.cuadromandointegral.dto.ColegioDto;
import com.salesianostriana.dam.cuadromandointegral.dto.CursoAcademicoDto;
import com.salesianostriana.dam.cuadromandointegral.dto.CursoAcademicoDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.dto.ObtenerPsmDtoByCaIdAndEv;
import com.salesianostriana.dam.cuadromandointegral.dto.PsmDto;
import com.salesianostriana.dam.cuadromandointegral.dto.PsmDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.Colegio;
import com.salesianostriana.dam.cuadromandointegral.model.CursoAcademico;
import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicador;
import com.salesianostriana.dam.cuadromandointegral.model.enums.Evaluacion;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;
import com.salesianostriana.dam.cuadromandointegral.servicios.ColegioServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.PsmServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.UserEntityServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.ValorIndicadorServicio;

/**
 * Test de el controlador PsmController
 * 
 * @author Daniel Santano Fernández
 *
 */

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = PsmController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class PsmControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private PsmServicio psmServicio;
	
	@MockBean
	private PsmDtoConverter psmDtoConverter;
	
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
	
	@MockBean
	private ColegioServicio colegioServicio;
	
	@MockBean
	private CursoAcademicoDtoConverter cursoAcademicoDtoConverter;
	
	@MockBean
	private ValorIndicadorServicio valorIndicadorServicio;
	
	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	public void whenPostCrearPsm_thenReturns201() throws Exception {
		//Da failure por que compara un objeto en string a null lo cual no deberia pasar no se donde esta el error
		ColegioDto cDto= ColegioDto.builder().id(2L).nombre("ColegioTest").build();
		CursoAcademicoDto caDto = CursoAcademicoDto.builder().id(3L).nombreCursoAcademico("CursoAcademicoTest").build();
		PsmDto dto = PsmDto.builder().id(1L).nombrePsm("PsmTest").evaluacion(Evaluacion.PRIMERA)
				.peso(100).colegioDto(cDto).cursoAcademico(caDto).listaValoresIndicadores(null).build();
		Psm p = new Psm(1L, "PsmTest", Evaluacion.PRIMERA, 100, null, null);
		Colegio c = new Colegio(2L, "ColegioTest", null, null, null, null, null, null);
		CursoAcademico ca = new CursoAcademico(3L, "CursoAcademicoTest",null, null, null, null);
		when(psmDtoConverter.convertPsmDtoToPsm(dto)).thenReturn(p);
		when(psmDtoConverter.convertPsmToPsmDto(p)).thenReturn(dto);
		when(colegioServicio.findById(dto.getColegioDto().getId())).thenReturn(c);
		when(cursoAcademicoDtoConverter.cursoAcademicoDtoToCursoAcademico(dto.getCursoAcademico())).thenReturn(ca);
		MvcResult result = mockMvc
				.perform(post("/psm/").contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated()).andReturn();
		String resultAsString2 = result.getResponse().getContentType();
		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(dto)).isEqualToIgnoringCase(resultAsString2);
	}
	
	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	public void whenPostCrearPsm_thenReturns204() throws Exception {
		//Da failure por que compara "null" con null no se como hacer para que no ponga las " a null
		PsmDto dto = PsmDto.builder().build();
		Psm p = Psm.builder().build();
		PsmDto dtoResp = null;
		when(psmDtoConverter.convertPsmDtoToPsm(dto)).thenReturn(p);
		when(psmDtoConverter.convertPsmToPsmDto(p)).thenReturn(dto);
		
		MvcResult result = mockMvc
				.perform(post("/psm/").contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isNoContent()).andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		String resultAsString2 = result.getResponse().getContentType();
		assertThat(objectMapper.writeValueAsString(null)).isEqualToIgnoringCase(resultAsString2);
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	public void whenPostObtenerPsmByCursoAcademicoIdAndEvaluacion_thenReturns200() throws Exception {
		ObtenerPsmDtoByCaIdAndEv dto = ObtenerPsmDtoByCaIdAndEv.builder().psmId(1L).cursoAcademicoId(2L)
				.evaluacion(Evaluacion.FINAL).build();
		Psm p = new Psm(1L, "PsmTest2", Evaluacion.FINAL, 10, null, null);
		PsmDto pDto = PsmDto.builder().id(1L).nombrePsm("PsmTest2").evaluacion(Evaluacion.FINAL)
				.peso(10).colegioDto(null).cursoAcademico(null).listaValoresIndicadores(null).build();
		when(psmServicio.findFirstByIdAndCursoAcademicoIdAndEvaluacion(dto.getPsmId(), dto.getCursoAcademicoId(),
				dto.getEvaluacion())).thenReturn(p);
		when(psmDtoConverter.convertPsmToPsmDto(p)).thenReturn(pDto);
		
		MvcResult result = mockMvc
				.perform(post("/psm/obtenerPsm").contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(pDto)).isEqualToIgnoringCase(resultAsString);
	}
	
	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	public void whenPostObtenerPsmByCursoAcademicoIdAndEvaluacion_thenReturns204() throws Exception {
		//Da error no se por que da NullPointerException
		ObtenerPsmDtoByCaIdAndEv dto = ObtenerPsmDtoByCaIdAndEv.builder().psmId(null).cursoAcademicoId(null)
				.evaluacion(null).build();
		Psm p = Psm.builder().build();
		PsmDto pDto = PsmDto.builder().build();
		List<ValorIndicador> l = new ArrayList<>();
		
		when(psmServicio.findFirstByIdAndCursoAcademicoIdAndEvaluacion(dto.getPsmId(), dto.getCursoAcademicoId(),
				dto.getEvaluacion())).thenReturn(p);
		when(psmDtoConverter.convertPsmToPsmDto(p)).thenReturn(pDto);
		when(valorIndicadorServicio.findAllByPsmId(p.getId())).thenReturn(l);
		
		MvcResult result = mockMvc
				.perform(post("/psm/obtenerPsm").contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isNoContent()).andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(p)).isEqualToIgnoringCase(resultAsString);
	}
	
	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	public void whenIndex_thenReturns200() throws Exception {
		Psm p = new Psm(1L, "PsmTest", Evaluacion.PRIMERA, 100, null, null);
		List<Psm> lp = new ArrayList<>();
		lp.add(p);
		when(psmServicio.findAll()).thenReturn(lp);
		mockMvc.perform(get("/psm/")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	public void whenIndex_thenReturns204() throws Exception {
		when(psmServicio.findAll()).thenReturn(Collections.emptyList());
		mockMvc.perform(get("/psm/")).andExpect(status().isNoContent());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	public void whenDelete_thenReturns204() throws Exception {
		//Da failure por que compara 204 con 404 cuando no deberia darse
		Long idPsm = 1L;
		when(psmServicio.existsById(idPsm)).thenReturn(true);
		when(psmServicio.eliminarPsmPorId(idPsm)).thenReturn(true);
		mockMvc.perform(delete("/delete/1")).andExpect(status().isNoContent())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	public void whenDelete_thenReturns404() throws Exception {
		//Da failure por que compara 400 con 404 cuando no deberia darse
		Long idPsm = 1L;
		when(psmServicio.existsById(idPsm)).thenReturn(false);
		when(psmServicio.eliminarPsmPorId(idPsm)).thenReturn(false);
		mockMvc.perform(delete("/delete/1")).andExpect(status().isBadRequest())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
}
