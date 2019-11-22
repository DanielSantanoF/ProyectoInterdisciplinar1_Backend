package com.salesianostriana.dam.cuadromandointegral.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class IndicadorCentro extends Indicador {

	@ManyToMany
	private List<Colegio> listaColegios;
	
	@Builder
	public IndicadorCentro(Long id,String nombreIndicador,boolean esPorcentaje,Proceso proceso,List<Colegio> listaColegios) {
		super(id,nombreIndicador,esPorcentaje,proceso);
		this.listaColegios=listaColegios;
	}
}
