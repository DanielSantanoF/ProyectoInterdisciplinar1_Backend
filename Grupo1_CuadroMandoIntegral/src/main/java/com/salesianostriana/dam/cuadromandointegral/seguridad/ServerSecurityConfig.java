package com.salesianostriana.dam.cuadromandointegral.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;


/**
 * Configuramos la seguridad de nuestra api
 * 
 * @author Daniel Santano Fernández Daniel 
 *
 */

@Configuration
//@Order(1)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@RequiredArgsConstructor
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * lo necesario para la clase
	 */
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * Bean de DaoAuthenticationProvider asignamos como damos la auth
	 * @return el proveedor de esa seguridad
	 */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    /**
     * El AuthenticationManager de la seguridad
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
