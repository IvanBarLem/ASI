package es.udc.asiproject.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import es.udc.asiproject.controller.security.token.JwtFilter;
import es.udc.asiproject.controller.security.token.JwtGenerator;
import es.udc.asiproject.persistence.model.enums.RoleType;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtGenerator jwtGenerator;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilter(new JwtFilter(authenticationManager(), jwtGenerator)).authorizeRequests()
				.antMatchers("/users/signUp", "/users/login", "/users/loginFromServiceToken").permitAll()
				.antMatchers(HttpMethod.GET, "/packs").hasRole(RoleType.AGENTE.name())
				.antMatchers("/products/**/hidden").hasAnyRole(RoleType.INFORMATICO.name(), RoleType.GERENTE.name())
				.antMatchers(HttpMethod.GET, "/products/**")
				.hasAnyRole(RoleType.AGENTE.name(), RoleType.INFORMATICO.name(), RoleType.GERENTE.name())
				.antMatchers("/products/**").hasRole(RoleType.INFORMATICO.name()).antMatchers("/statistics/**")
				.hasRole(RoleType.GERENTE.name()).antMatchers("/**").hasRole(RoleType.GERENTE.name());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return source;
	}
}
