package com.salesianostriana.dam.cuadromandointegral.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;


import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class IndicadorUnidad extends Indicador {

	@ManyToMany
	private List<Unidad> listaUnidades;
	
	@Builder
	public IndicadorUnidad(Long id,String nombreIndicador,boolean esPorcentaje,Proceso proceso,List<Unidad> listaUnidades) {
		super(id,nombreIndicador,esPorcentaje,proceso);
		this.listaUnidades=listaUnidades;
	}
}
