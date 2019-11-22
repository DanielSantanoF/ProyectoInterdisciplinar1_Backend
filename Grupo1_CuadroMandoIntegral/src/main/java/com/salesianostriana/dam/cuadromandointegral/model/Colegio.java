package com.salesianostriana.dam.cuadromandointegral.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class Colegio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombreColegio;
	

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "colegio")
	private List<Etapa> etapas;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "colegio")
	private List<ValorIndicadorCentro> listaValoresIndicadorCentro;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "colegio")
	private List<PuntoControl> listaPuntosDeControl;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "colegio")
	private List<Psm> listaPsm;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(fetch = FetchType.EAGER)
	private List<IndicadorCentro> listaIndicadorCentro;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "colegio")
	private List<UserEntity> listaUsuarios;
	
	public void addPsm(Psm psm) {
		listaPsm.add(psm);
	}

	public void deletePsm(Psm psm) {
		listaPsm.remove(psm);
	}

	public void addValorIndicadorCentro(ValorIndicadorCentro vic) {
		listaValoresIndicadorCentro.add(vic);
	}

	public void deleteValorIndicadorCentro(ValorIndicadorCentro vic) {
		listaValoresIndicadorCentro.remove(vic);
	}
}
