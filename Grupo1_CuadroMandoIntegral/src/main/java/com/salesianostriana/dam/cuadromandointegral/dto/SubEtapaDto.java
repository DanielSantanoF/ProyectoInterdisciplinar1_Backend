package com.salesianostriana.dam.cuadromandointegral.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase dto para una SubEtapa
 * 
 * @author jldiez
 * 
 */

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SubEtapaDto {
	
	private Long id;
	private String nombreSubEtapa;

}
