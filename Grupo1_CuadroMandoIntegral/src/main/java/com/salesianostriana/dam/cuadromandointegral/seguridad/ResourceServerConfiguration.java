package com.salesianostriana.dam.cuadromandointegral.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.salesianostriana.dam.cuadromandointegral.errores.CustomAccessDeniedHandler;
import com.salesianostriana.dam.cuadromandointegral.errores.CustomAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

/**
 * Configuración para el servidor de recursos
 * 
 * @author Daniel Santano Fernández 
 *
 */

@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	/**
	 * Lo necesario para la clase
	 */
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	/**
	 * Configuracion del servidor
	 */
	@Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("api");
    }
	
	/**
	 * Bean que indica el cifrado de la contraseña
	 * @return BCryptPasswordEncoder la manera de cifrar
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Configuracion de todas las rutas de acceso al api
	 */
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .antMatcher("/**")
                .authorizeRequests()
                //LINEA PARA PODER USAR LA CONSOLA H2
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/superAdmin/**").hasAuthority("SUPER_ADMIN")
				.antMatchers("/file/**").authenticated()
				.antMatchers("/usuario/**").authenticated()
				.antMatchers("/psm/**").authenticated()
				.antMatchers("/admin/**").authenticated()
				.antMatchers("/registrarse/**").authenticated()
				.antMatchers("/colegio/**").authenticated()
                .antMatchers("/puntosControl/**").authenticated()
                .antMatchers("/cursoacademico/**").authenticated()
                .antMatchers("/indicador/**").authenticated()
                .antMatchers("/procesos/**").authenticated()
                .antMatchers("/cursos/**").authenticated()
                .antMatchers("/etapa/**").authenticated()
                .antMatchers("/curso/**").authenticated()
                .antMatchers("/unidad/**").authenticated()
				.anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).accessDeniedHandler(new CustomAccessDeniedHandler());
        
        http.cors().and().csrf().disable();
        http.headers().frameOptions().disable();
    }
	
}
