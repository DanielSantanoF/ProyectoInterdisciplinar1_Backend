package com.salesianostriana.dam.cuadromandointegral.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.salesianostriana.dam.cuadromandointegral.model.enums.Evaluacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Psm {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombrePSM;
	@Enumerated(EnumType.STRING)
	private Evaluacion evaluacion;
	private Integer peso;

	@ManyToOne
	private Colegio colegio;
	
	@ManyToOne
	private CursoAcademico cursoAcademico;
}
