package com.salesianostriana.dam.cuadromandointegral.model;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * Clase modelo para usuario implementa UserDetails
 * 
 * @author Daniel Santano Fernández 
 *
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class UserEntity implements UserDetails{
	
	/**
	 * Lo necesario para la clase
	 */
	private static final long serialVersionUID = 736837059920807011L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;
	
	@NotEmpty
    @Email
    private String email;

	@JsonIgnore
    @ToString.Exclude
	private String password;

	@NotNull
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> role;

	@CreatedDate
	private LocalDateTime createdAt;

	@Builder.Default
	private LocalDateTime lastPasswordChangedAt = LocalDateTime.now();

	@ManyToOne
	@ElementCollection(fetch = FetchType.EAGER)
	private Colegio colegio;
	
	/**
	 * Metodo para aasignar authorities al usuario
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	
	/**
	 * Metodo para controlar si la cuenta esta expirada
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Metodo para controlar si la cuenta no esta blockeada
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Metodo para comprobar si la cuenta no esta expirada
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Metodo para comprobar si esta activo el usuario
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
