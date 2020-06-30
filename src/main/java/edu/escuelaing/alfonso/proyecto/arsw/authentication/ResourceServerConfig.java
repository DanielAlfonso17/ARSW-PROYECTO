package edu.escuelaing.alfonso.proyecto.arsw.authentication;


import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


		//reglas de seguridad de los endpoints de las rutas hacia los recursos de nuestra app ej permiso a todos de listado cliente
		//crud para solo los admin 
			
		@Override
		public void configure(HttpSecurity http) throws Exception {
			//forma programatica
			//permitir a todos acceso a consulta de tabla clientes la paginacion tambien para todos y ver imagenes asterisco significa todo lo que venga despues de esta ruta recursivo publico
			http.authorizeRequests().antMatchers(HttpMethod.GET,"/**").permitAll();
			http.authorizeRequests().antMatchers(HttpMethod.POST,"/**").permitAll();
			
			//.antMatchers(HttpMethod.GET, "/comprador/productos/**").hasRole("USER")
			//.antMatchers(HttpMethod.POST, "/vendedor/**").hasRole("ADMIN")
			//antMatchers(HttpMethod.GET, "/vendedor/**").hasRole("ADMIN")
			
		}
		//cors es un mecanismo que usa las cabezaras http para permitir que un cliente que esta en otro dominio distinto al backend, tenga permisos para acceder a los recursos del backend por spring security OAUTH2
		
		@Bean
		public CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration config = new CorsConfiguration();
			//indicamos el dominio donde reside nuestra aplicacion cliente (angular) podemos configurar varios con * podemos definir todos los dominios
			//para produccion en asterisco agregamos nombre de dominio o ip para produccion * aceota cualquier origen
			//config.setAllowedOrigins(Arrays.asList("http://localhost:4200","*")); 
			config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
			//configuracion de metodos o verbos que permitiremos en nuetro backend 
			config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
			//configuracion permitir credenciales
			config.setAllowCredentials(true);
			//Permitir cabeceras 
			config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
			//de la clase cors no la REACTIVA!!!
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			//Todas las rutas y endpoints del backend del backend 
			source.registerCorsConfiguration("/**", config);
			return source; 
		}
		//registrar un filtro un bean en la prioridad mas alta de los filtros de sprint 
		//para que se aplique al servidor de autorizacion cuando accedamos a los endpoint para generar el token al autenticarnos y para validar el token
		//filtro de cors le pasamos la configuracion de arriba y lo registramos dentro dl stack del conjunto de filtro de spring y le dimos prioridad mas alta
		@Bean
		public FilterRegistrationBean<CorsFilter> corsFilter(){
			//pasamos instancia de corsfilter u al cors filter le pasamos la configuracion de arriba 
			FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
			//dar un orden bajo, entre mas bajo el orden mas es la presedencia o prioridad 
			bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
			return bean;
		}

	


}
