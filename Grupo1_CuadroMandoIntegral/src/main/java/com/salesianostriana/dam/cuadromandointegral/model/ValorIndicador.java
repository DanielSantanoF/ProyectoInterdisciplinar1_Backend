package com.salesianostriana.dam.cuadromandointegral.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

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
@Inheritance(strategy = InheritanceType.JOINED)
@Builder
public class ValorIndicador {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Double valorReal;
	private Double valorConforme;
	private boolean esNoAplica;
	
	@ManyToOne
	private Psm psm;
	
	@ManyToOne 
	private CursoAcademico cursoAcademico;
	
	
}