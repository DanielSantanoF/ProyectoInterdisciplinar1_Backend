package com.salesianostriana.dam.cuadromandointegral.files;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response de UploadFile
 * 
 * @author Daniel Santano Fernández 
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResponse {

	/*
	 * Clase de respuesta a peticiones sobre files al api
	 */
	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;



}
