package com.salesianostriana.dam.cuadromandointegral.errores;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.util.StringUtils;

/**
 * Excepción si no se encuentra la entidad
 * 
 * @author Daniel Santano Fernández 
 *
 */
public class EntityNotFoundException extends RuntimeException{

	 /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3137934318423049774L;

	/**
	 * Metodo para indicar que la excepcion no se ha encontrado
	 * @param clazz clase que se usa
	 * @param searchParamsMap parametros por los que se busca
	 */
	@SuppressWarnings("rawtypes")
	public EntityNotFoundException(Class clazz, String... searchParamsMap) {
	        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
	    }

	    private static String generateMessage(String entity, Map<String, String> searchParams) {
	        return StringUtils.capitalize(entity) +
	                " no se han encontrado los parametros " +
	                searchParams;
	    }

	    private static <K, V> Map<K, V> toMap(
	            Class<K> keyType, Class<V> valueType, Object... entries) {
	        if (entries.length % 2 == 1)
	            throw new IllegalArgumentException("Entradas invalidas");
	        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
	                .collect(HashMap::new,
	                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
	                        Map::putAll);
	    }
}
