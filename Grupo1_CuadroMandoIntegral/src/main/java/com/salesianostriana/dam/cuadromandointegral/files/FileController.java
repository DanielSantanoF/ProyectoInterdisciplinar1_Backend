package com.salesianostriana.dam.cuadromandointegral.files;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.salesianostriana.dam.cuadromandointegral.dto.PsmDtoConverter;
import com.salesianostriana.dam.cuadromandointegral.servicios.PsmServicio;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

/**
 * Controlador de files
 * 
 * @author Daniel Santano Fernández 
 *
 */

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

	/**
	 * Lo necesario para el controlador
	 */
	private final FileStorageService fileStorageService;
	private final PsmDtoConverter psmDtoConverter;
	private final PsmServicio psmServicio;

	//https://stackoverflow.com/questions/21329426/spring-mvc-multipart-request-with-json
	/*
	 * EN ESTE ENLACE EL 3º COMENTARIO LA PARTE 
	 * his must work!

		@RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    Advertisement storeAd(@RequestPart("ad") String adString, @RequestPart("file") MultipartFile file) throws IOException {

        Advertisement jsonAd = new ObjectMapper().readValue(adString, Advertisement.class);
//do whatever you want with your file and jsonAd
	DEBERIAMOS PASAR EL DTO COMO STRING Y CON EL OBJECTMAPPER LO TRANSFORMARIA A NUESTRO DTO NECESARIO PARA PODER 
	PROCESAR EL ARCHIVO CSV SEGUN EL PSM QUE CREAREMOS CON EL DTO CONVERTER QUE PASAMOS EN STRING QUE LUEGO PASA A SU CLASE
	DTO Y DE DTO A ENTIDAD TRAS ESO SE PROCESA E INSERTAN DATOS
	 * */
	@PostMapping(value = "/uploadCsvByPsm",consumes = {"multipart/form-data"}) //@RequestPart("json") CreatePsmDto dto
	public ResponseEntity<?> uploadCsvByPsm(@RequestPart("file") MultipartFile file, @RequestPart("json") String dto) {
		if(file.isEmpty() || dto.equals(null)) {
			return ResponseEntity.noContent().build();
		}else {
		String fileName = fileStorageService.store(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();
		UploadFileResponse uploadFileResponse = new UploadFileResponse(fileName, fileDownloadUri, 
				file.getContentType(), file.getSize());
		String extension = StringUtils.getFilenameExtension(fileName);
		//ESTO SE DEBERIA PROCESAR TRAS EL PROCESADO DEL dto String a PsmDto CON EL COMENTARIO DE ARRIBA
		//Psm p = psmDtoConverter.convertPsmDtoToPsm(dto);
		//psmServicio.save(p);
		//Si el archivo subido es csv lo procesa segun el psm creado
		if(extension == "xls" || extension == "xlsx") {
			fileStorageService.procesarFicheroCsv(fileName, null);//NULL ES EL PSM
		}
		return new ResponseEntity<UploadFileResponse>(uploadFileResponse, HttpStatus.CREATED);
		}
	}
	
	/**
	 * Metodo para subir archivos al api
	 * @param file el archivo a subir
	 * @return uploadFileResponse con todos los datos
	 */
	@PostMapping("/uploadFile") 
	public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file) {
		if(file.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
		String fileName = fileStorageService.store(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();
		UploadFileResponse uploadFileResponse = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
		return new ResponseEntity<UploadFileResponse>(uploadFileResponse, HttpStatus.CREATED);
		}
	}


	/**
	 * Metodo para descargar el archivo segun la url que se da al suibir un fichero
	 * @param fileName el nombre del fichero
	 * @param request peticion de descarga
	 * @return el fichero a descargar
	 */
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadAsResource(fileName);
		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			log.info("Could not determine file type.");
		}
		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
