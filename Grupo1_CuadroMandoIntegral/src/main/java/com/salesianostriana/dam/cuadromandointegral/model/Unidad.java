package com.salesianostriana.dam.cuadromandointegral.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
public class Unidad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombreUnidad;
	private Integer peso;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	private Curso curso;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "unidad")
	private List<ValorIndicadorUnidad> listaValoresIndicadorUnidad;

	@ManyToMany
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<IndicadorUnidad> listaIndicadoresUnidad;
	
	public void addIndicadorUnidad(IndicadorUnidad iu) {
		listaIndicadoresUnidad.add(iu);
		iu.getListaUnidades().add(this);
	}

	public void deleteIndicadorUnidad(IndicadorUnidad iu) {
		listaIndicadoresUnidad.remove(iu);
		iu.getListaUnidades().remove(this);
	}
	
	public void addValorIndicadorUnidad(ValorIndicadorUnidad viu) {
		listaValoresIndicadorUnidad.add(viu);
	}

	public void deleteValorIndicadorUnidad(ValorIndicadorUnidad viu) {
		listaValoresIndicadorUnidad.remove(viu);
	}
}
