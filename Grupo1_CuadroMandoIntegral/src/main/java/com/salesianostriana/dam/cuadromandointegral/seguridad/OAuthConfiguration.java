package com.salesianostriana.dam.cuadromandointegral.seguridad;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import lombok.RequiredArgsConstructor;

/**
 * Configuración para propietario de recursos y servidor de autorización
 * 
 * @author Daniel Santano Fernández 
 *
 */

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

	/**
	 * Lo necesario para la clase
	 */
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userService;

	/**
	 * El client Id del JWT
	 */
	@Value("${jwt.clientId:g1-proyecto-uno}")
    private String clientId;

	/**
	 * El client secret del JWT
	 */
    @Value("${jwt.client-secret:secret}")
    private String clientSecret;

    /**
     * Tiempo que dura el JWT
     */
    @Value("${jwt.accessTokenValidititySeconds:43200}") // 12 hours
    private int accessTokenValiditySeconds;

    /**
     * jwt.authorizedGrantTypes del JWT
     */
    @Value("${jwt.authorizedGrantTypes:password,authorization_code,refresh_token}")
    private String[] authorizedGrantTypes;

    /**
     * Duracion del JWT refresh
     */
    @Value("${jwt.refreshTokenValiditySeconds:2592000}") // 30 days
    private int refreshTokenValiditySeconds;

    /**
     * Configuracion de OAuth2
     */
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret))
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
                .authorizedGrantTypes(authorizedGrantTypes)
                .scopes("read", "write")
                .resourceIds("api");
    }

	/**
	 * Configuracion de los endpoints de la autorizacion
	 */
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .accessTokenConverter(accessTokenConverter())
                .userDetailsService(userService)
                .authenticationManager(authenticationManager);
    }

    /**
     * Bean del accestokenconverter 
     * @returnel converter
     */
    @Bean
    JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        return converter;
    }
}
