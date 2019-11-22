package com.salesianostriana.dam.cuadromandointegral;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.cuadromandointegral.files.FileStorageService;
import com.salesianostriana.dam.cuadromandointegral.model.UserEntity;
import com.salesianostriana.dam.cuadromandointegral.repositorios.UserEntityRepositorio;

/**
 * 
 * @author Grupo 1 Ev 1º Salesianos Triana San Pedro Curso: 1DAM 2019-2020
 *
 */
@SpringBootApplication
@EnableJpaAuditing
public class Proyecto1Tarea1Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Proyecto1Tarea1Application.class, args);

		/* FORMA DE PROVAR EL CSV PROCESADOR
		try {
			List<ValorIndicador> resultado = CsvProcesador.procesaCsvValorIndicador("PSM_Final_csv.csv", true);
			resultado.stream().limit(100).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	//@Profile("dev")
	@Bean
	public CommandLineRunner initData(FileStorageService fileStorageService, BCryptPasswordEncoder passwordEncoder,
			UserEntityRepositorio userEntityRepositorio) {
		return args -> {
			// fileStorageService.deleteAll();
			fileStorageService.init();
			
			//Cifrar contraseña de los usuarios en el data sql
			List<UserEntity> lista = userEntityRepositorio.findAll();
			for(UserEntity user : lista) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userEntityRepositorio.save(user);
			}
		};
	}

}
