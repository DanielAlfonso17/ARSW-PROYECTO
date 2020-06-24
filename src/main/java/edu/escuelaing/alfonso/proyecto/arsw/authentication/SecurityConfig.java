package edu.escuelaing.alfonso.proyecto.arsw.authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired 
	private UserDetailsService usuarioService;
	//Forma de registrar objetos que retorna un metodo nos permite atraves de un metodo el metodo que retorna lo registra en el contenedor de sprint 
	//despues se puede inyectar en otra clase spring
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}
	
	//para que podamos inyectar en la clase authorizationserverconfig el manager
	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	} 
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//permitir a todos los usuarios acceso a consulta de tabla clientes
		http.authorizeRequests()
		//las demas request solo para usuarios autenticados, put,post,delete
		.anyRequest().authenticated()
		//manejo de sesion deshabilitador por el lado de spring deshabilitamos el csrf manejo de sesiones dehabilitado porque usaremos token
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
