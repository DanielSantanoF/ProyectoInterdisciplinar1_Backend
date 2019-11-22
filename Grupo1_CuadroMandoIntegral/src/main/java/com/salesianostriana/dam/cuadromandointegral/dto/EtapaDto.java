package com.salesianostriana.dam.cuadromandointegral.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder 

/**
 * Clase dto del modelo Etapa
 * 
 * @author jldiez
 * 
 */
public class EtapaDto {
	
		private Long id;
		private String nombreSubEtapa;
		private ColegioDto colegio;
		
}
