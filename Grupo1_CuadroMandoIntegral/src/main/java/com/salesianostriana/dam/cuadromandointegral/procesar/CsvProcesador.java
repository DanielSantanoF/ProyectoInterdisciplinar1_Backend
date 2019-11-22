package com.salesianostriana.dam.cuadromandointegral.procesar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicador;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicadorCentro;
import com.salesianostriana.dam.cuadromandointegral.model.ValorIndicadorUnidad;
import com.salesianostriana.dam.cuadromandointegral.servicios.IndicadorCentroServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.IndicadorUnidadServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.UnidadServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.ValorIndicadorCentroServicio;
import com.salesianostriana.dam.cuadromandointegral.servicios.ValorIndicadorUnidadServicio;
/**
 * Procesador para importar un Psm desde un CSV
 * @author José Antonio Llamas
 *
 */
@Component
public class CsvProcesador {

	private static final String DELIMITADOR = ";";
	@SuppressWarnings("unused")
	private static final int C_PROCESO = 0;
	private static final int C_INDICADOR = 1;
	@SuppressWarnings("unused")
	private static final int C_ETAPA = 2;
	@SuppressWarnings("unused")
	private static final int C_CICLO = 3;
	@SuppressWarnings("unused")
	private static final int C_CURSO = 4;
	private static final int C_UNIDAD = 5;
	private static final int C_VALORR = 6;
	private static final int C_VALORC = 7;

	private static String comillas;

	private static IndicadorCentroServicio ics;
	private static ValorIndicadorCentroServicio vics;
	private static UnidadServicio us;
	private static IndicadorUnidadServicio ius;
	private static ValorIndicadorUnidadServicio vius;

	@Autowired
	public void setServicios(IndicadorCentroServicio ics, ValorIndicadorCentroServicio vics, UnidadServicio us,
			IndicadorUnidadServicio ius, ValorIndicadorUnidadServicio vius) {

		CsvProcesador.ics = ics;
		CsvProcesador.vics = vics;
		CsvProcesador.us = us;
		CsvProcesador.ius = ius;
		CsvProcesador.vius = vius;
	}

	public static List<ValorIndicador> procesaCsvValorIndicador(String fichero,Psm psm, boolean skipHeaders)
			throws IOException {
		return Files.lines(Paths.get(fichero)).skip(skipHeaders ? 1 : 0).map(str->mapToValorIndicador(str,psm))
				.collect(Collectors.toList());
	}

	/**
	 * Creamos los ValoresIndicadores en los que se insertan desde el CSV
	 * @param linea
	 * @return
	 */
	public static ValorIndicador mapToValorIndicador(String linea,Psm psm) {

		String[] datos = linea.split(DELIMITADOR);
		ValorIndicador vi = ValorIndicador.builder().cursoAcademico(psm.getCursoAcademico())
				.esNoAplica(datos[C_VALORR].equals("NA")).psm(psm)
				.valorConforme(datos[C_VALORC].equals("NaN") ? 0 : Double.valueOf(datos[C_VALORC].replace(",", ".")))
				.valorReal(datos[C_VALORR].equals("NA") ? 0 : Double.valueOf(datos[C_VALORR].replace(",", ".")))
				.build();

		if (datos[C_UNIDAD].matches("centro")) {
			deValorIndicadorACentro(vi, linea);
		} else {
			deValorIndicadorAUnidad(vi, linea);
		}

		return vi;
	}

	/**
	 * Si el ValorIndicador es de centro, creamos el ValorIndicadorCentro y los asociamos
	 * @param vi
	 * @param linea
	 */
	public static void deValorIndicadorACentro(ValorIndicador vi, String linea) {
		String[] datos = linea.split(DELIMITADOR);

		ValorIndicadorCentro vic = new ValorIndicadorCentro();
		vic.setColegio(vi.getPsm().getColegio());
		vic.setCursoAcademico(vi.getCursoAcademico());
		vic.setEsNoAplica(vi.isEsNoAplica());
		vic.setPsm(vi.getPsm());
		vic.setValorConforme(vi.getValorConforme());
		vic.setValorReal(vi.getValorReal());
		if (datos[C_INDICADOR] != " '' ") {
			comillas = datos[C_INDICADOR];

			vic.setIndicadorCentro(ics.findByName(comillas));
			
		} else {
			
			vic.setIndicadorCentro(ics.findByName(comillas));
			
		}
		vics.save(vic);
	}

	/**
	 * Si el ValorIndicador es de unidad, creamos el ValorIndicadorUnidad y los asociamos
	 * @param vi
	 * @param linea
	 */
	public static void deValorIndicadorAUnidad(ValorIndicador vi, String linea) {
		String[] datos = linea.split(DELIMITADOR);

		ValorIndicadorUnidad viu = new ValorIndicadorUnidad();
		viu.setCursoAcademico(vi.getCursoAcademico());
		viu.setEsNoAplica(vi.isEsNoAplica());
		viu.setPsm(vi.getPsm());
		viu.setUnidad(us.findByName(datos[C_UNIDAD]));
		viu.setValorConforme(vi.getValorConforme());
		viu.setValorReal(vi.getValorReal());
		if (datos[C_INDICADOR] != " '' ") {
			comillas = datos[C_INDICADOR];

			viu.setIndicadorUnidad(ius.findByName(comillas));

		} else {

			viu.setIndicadorUnidad(ius.findByName(comillas));
		
		}
		vius.save(viu);
	}

}
