package com.salesianostriana.dam.cuadromandointegral.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicador;
import com.salesianostriana.dam.cuadromandointegral.servicios.ColegioServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.ValorIndicadorServicio;

import lombok.RequiredArgsConstructor;

/**
 * Dto converter de Psm
 * 
 * @author Daniel Santano Fernández
 *
 */

@RequiredArgsConstructor
@Component
public class PsmDtoConverter {
	
	/**
	 * lo necesario para la clase
	 */
	private final ColegioDtoConverter colegioConverter;
	private final ColegioServicio colegioServicio;
	private final CursoAcademicoDtoConverter cursoAcademicoDtoConverter;
	private final ValorIndicadorServicio valorIndicadorServicio;
	private final ValorIndicadorDtoConverter valorIndicadorDtoConverter;
	
	/**
	 * metodo para convertir un psm a dto
	 * @param p psm a convertir 
	 * @return dto generado
	 */
	public PsmDto convertPsmToPsmDto(Psm p) {
		List<ValorIndicador> l = valorIndicadorServicio.findAllByPsmId(p.getId());
		return PsmDto.builder()
				.id(p.getId())
				.nombrePsm(p.getNombrePSM())
				.evaluacion(p.getEvaluacion())
				.peso(p.getPeso())
				.colegioDto(colegioConverter.convertColegioToColegioDto(p.getColegio()))
				.cursoAcademico(cursoAcademicoDtoConverter.convertCursoAcademicoToCursoAcademicoDto(p.getCursoAcademico()))
				.listaValoresIndicadores(l.stream().map(valorIndicadorDtoConverter :: convertValorIndicadorToValorIndicadorDto)
						.collect(Collectors.toList()))
				.build();
		
	}
	
	/**
	 * metodo para pasar el psm filtrado a dto
	 * @param p psm filtrado
	 * @return dto generado
	 */
	public PsmDto convertPsmFiltradoToPsmDto(Psm p) {
		List<ValorIndicador> l = valorIndicadorServicio.findAllByPsmId(p.getId());
		if(l.isEmpty()) {
			return PsmDto.builder()
					.id(p.getId())
					.nombrePsm(p.getNombrePSM())
					.evaluacion(p.getEvaluacion())
					.peso(p.getPeso())
					.colegioDto(colegioConverter.convertColegioToColegioDto(p.getColegio()))
					.cursoAcademico(cursoAcademicoDtoConverter.convertCursoAcademicoToCursoAcademicoDto(p.getCursoAcademico()))
					.build();
		}else {
			return PsmDto.builder()
					.id(p.getId())
					.nombrePsm(p.getNombrePSM())
					.evaluacion(p.getEvaluacion())
					.peso(p.getPeso())
					.colegioDto(colegioConverter.convertColegioToColegioDto(p.getColegio()))
					.cursoAcademico(cursoAcademicoDtoConverter.convertCursoAcademicoToCursoAcademicoDto(p.getCursoAcademico()))
					.listaValoresIndicadores(l.stream().map(valorIndicadorDtoConverter :: convertValorIndicadorToValorIndicadorDto)
							.collect(Collectors.toList()))
					.build();
		}
	}
	
	/**
	 * metodo para pasar de dto a entidad de psm
	 * @param pDto dto a convertir
	 * @return entidad generada
	 */
	public Psm convertPsmDtoToPsm(PsmDto pDto) {
		return Psm.builder()
				.id(pDto.getId())
				.nombrePSM(pDto.getNombrePsm())
				.evaluacion(pDto.getEvaluacion())
				.peso(pDto.getPeso())
				.colegio(colegioServicio.findById(pDto.getColegioDto().getId()))
				.cursoAcademico(cursoAcademicoDtoConverter.cursoAcademicoDtoToCursoAcademico(pDto.getCursoAcademico()))
				.build();
	}
	
	/**
	 * metodo para pasar de lista psm a lista dto
	 * @param l lista de psm
	 * @return lista de dto
	 */
	public List<PsmDto> convertListPsmToListPsmDto(List<Psm> l){
		return l.stream().map(this :: convertPsmToPsmDto)
				.collect(Collectors.toList());
	}
	
}
