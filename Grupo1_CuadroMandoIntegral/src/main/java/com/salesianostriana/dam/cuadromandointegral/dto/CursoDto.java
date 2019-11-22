package com.salesianostriana.dam.cuadromandointegral.dto;


import java.util.List;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Clase dto del modelo curso
 * @author Miguel Lázaro Domínguez
 *
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CursoDto {

	private Long id;
	private String nombreEtapa;
	private String nombreCurso;
	private Integer peso;
	private List<UnidadDto> unidades;
	
}
