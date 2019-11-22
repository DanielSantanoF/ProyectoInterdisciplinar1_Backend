package com.salesianostriana.dam.cuadromandointegral.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
public class ValorIndicadorCentro extends ValorIndicador {

	@ManyToOne
	private Colegio colegio;
	
	@ManyToOne
	private IndicadorCentro indicadorCentro;
}
