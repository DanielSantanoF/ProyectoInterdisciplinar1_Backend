package com.salesianostriana.dam.cuadromandointegral.files;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.cuadromandointegral.model.Psm;
import com.salesianostriana.dam.cuadromandointegral.procesar.CsvProcesador;

/**
 * Implementación de un {@link StorageService} que almacena
 * los ficheros subidos dentro del servidor donde se ha desplegado
 * la apliacación.
 * 
 * ESTO SE REALIZA ASI PARA NO HACER MAS COMPLEJO EL EJEMPLO.
 * EN UNA APLICACIÓN EN PRODUCCIÓN POSIBLEMENTE SE UTILICE
 * UN ALMACÉN REMOTO.
 * 
 * 
 * @author Equipo de desarrollo de Spring
 *
 */

@Service
public class FileStorageService implements StorageService{

	@Value("${file.upload-dir}")
	private String uploadDir;
	
	private Path fileStorageLocation;
	private CsvProcesador csvProcesador;


	/**
	 * Declaramos donde se van a guardar los ficheros que subamos
	 */
    @Override
    public void init() {
        this.fileStorageLocation = Paths.get(uploadDir)
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /**
     * Método que almacena un fichero en el almacenamiento secundario
     * desde un objeto de tipo MultipartFile
     * 
     * Modificamos el original del ejemplo de Spring para cambiar el nombre
     * del fichero a almacenar. Como lo asociamos al Empleado que se ha
     * dado de alta, usaremos el ID de empleado como nombre de fichero.
     * 
     */
    @Override
    public String store(MultipartFile file) {
        // Normalize file name
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = StringUtils.getFilenameExtension(filename);
        String justFilename = filename.replace("."+extension, "");
        String storedFilename = System.currentTimeMillis() + "_" + justFilename + "." + extension;

        try {
            // Check if the file's name contains invalid characters
            if(storedFilename.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + storedFilename);
            }
            
            if (file.isEmpty()) {
                throw new FileStorageException("Failed to store empty file " + filename);
            }
            
         // Copy file to the target location (Replacing existing file with the same name)
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.fileStorageLocation.resolve(storedFilename),
                    StandardCopyOption.REPLACE_EXISTING);
                return storedFilename;
            }

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + storedFilename + ". Please try again!", ex);
        }
    }

    /**
     * Método que es capaz de cargar un fichero a partir de su nombre
     * Devuelve un objeto de tipo Resource
     */
    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }
    
    /**
     * Método que devuelve la ruta de todos los ficheros que hay
     * en el almacenamiento secundario del proyecto.
     */
    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.fileStorageLocation, 1)
                .filter(path -> !path.equals(this.fileStorageLocation))
                .map(this.fileStorageLocation::relativize);
        }
        catch (IOException e) {
            throw new FileStorageException("Failed to read stored files", e);
        }

    }
    
    /**
     * Método que es capaz de cargar un fichero a partir de su nombre
     * Devuelve un objeto de tipo Path
     */
    @Override
    public Path load(String filename) {
        return fileStorageLocation.resolve(filename);
    }
    
    /**
     * Método que elimina todos los ficheros del almacenamiento
     * secundario del proyecto.
     */
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(fileStorageLocation.toFile());
    }
    
    /**
     * Método para borrar un fichero por su nombre
     */
    @Override
	public void delete(String filename) {
		String justFilename = StringUtils.getFilename(filename);
		try {
			Path file = load(justFilename);
			Files.deleteIfExists(file);
		} catch (IOException e) {
			throw new FileStorageException("Error al eliminar un fichero", e);
		}
		
	}
    
    /**
     * Metodo para procesar un fichero si el que se sube es un csv
     * @param fileName Nombre del fichero
     */
    @SuppressWarnings("static-access")
	public void procesarFicheroCsv(String fileName, Psm psm) {
        String extension = StringUtils.getFilenameExtension(fileName);
        if(extension == "xls" || extension == "xlsx") {
        	try {
				csvProcesador.procesaCsvValorIndicador(fileName,psm, true);
        	} catch (IOException e) {
    			e.printStackTrace();
    		}
        }
    }
    
    
}
