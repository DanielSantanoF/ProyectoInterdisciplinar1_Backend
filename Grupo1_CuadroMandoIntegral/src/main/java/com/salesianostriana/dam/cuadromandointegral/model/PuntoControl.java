package com.salesianostriana.dam.cuadromandointegral.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class PuntoControl {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;

	@OneToMany
	private List<Psm> listaPsm;

	@ManyToOne
	private Colegio colegio;

	public void addPsm(Psm psm) {
		listaPsm.add(psm);
	}

	public void deletePsm(Psm psm) {
		listaPsm.remove(psm);
	}
}
