package edu.escuelaing.alfonso.proyecto.arsw.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;




//manejo y validacion de token 
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter{
	//inyectamos bean de password encrypt en springsecurityconfig
		@Autowired
		private BCryptPasswordEncoder passwordEncoder;
		//necesitamos authentication manager configurado para usarlo en el proceso del login
		@Autowired
		//inyectamos con autowired y qualifier para definir el nombre del metodo del manager en springsecurityconfig
		@Qualifier("authenticationManager")
		private AuthenticationManager authenticationManager;
	
		//Metodo que configura los permisos de los endpoints 
		//Tenemos dos endpoints en authorization server uno para autenticarnos e iniciar sesion y lo manda al usuario esa ruta dbe ser publica
		//ya que cualquier usuario puede iniciar sesion y recibir un token
		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			//generar el token cuando se autentica
			security.tokenKeyAccess("permitAll()")
			//dar permiso a endpoint que se encargara de validar token cada vez que accedamos a pagina protegida 
			//debemos enviar nuestro token en la cabecera de la peticion
			//validar token y su firma
			.checkTokenAccess("isAuthenticated()");
			//estos dos endpoints estan protegidos por autenticacion via http basic utilizando las credenciales del cliente, la aplicacion 
		}
		//registro de todas las aplicaciones que accederan o consumen este servicio rest, se registran uno por uno 
		//con su cliente id y codigo contraseña si tuviera varias app o clientes que queiran acceder al backend cada 
		//app debe tener su propio cliente y credenciales
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			//nombre del cliente
			clients.inMemory().withClient("reactapp")
			//contraseña encriptada con passwordweconder
			.secret(passwordEncoder.encode("12345"))
			//el permiso que tiene e cliente(Aplicacion) podra leer crear datos modificar eliminar 
			.scopes("read","write")
			//como sera el tipo de autenticacion como lo obtendremos el token tanto para usuarios como para el cliente puede ser otro tipo de auten
			//1) credenciales 2)codigo autorizacion via redireccionamiento url 3)implicita mas debil parecida a codigo de autorizacion enviamos el cliente id y password en la redireccion url recibimos el token
			//refresh_token nos permite obtener un token de acceso renovado o actualizado y continuar accediendo a los recursos sin iniciar sesion 
			.authorizedGrantTypes("password", "refresh_token")
			//tiempo de caducidad del token
			.accessTokenValiditySeconds(3600)
			//tiempo validacion del refresh token
			.refreshTokenValiditySeconds(3600);
			
		}
		//metodo encargado de proceso de autenticacion y validacion del token y su firma 
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			
			
			
			endpoints.authenticationManager(authenticationManager)
			//opcional con lo de abajo
			.tokenStore(tokenStore())
			//componente encargado de manejar cosas relacionadas al token, almacena datos autenticacion de usuario no datos sensibles nombre, username, identificacion se encarga de traducir datos codificados del usuario a una info decodificada para que authenticacion 
			//manager con Oauth2 pueda validar y autenticar token
			.accessTokenConverter(accessTokenConverter());
			
		}
		
		
		//es opcional lo maneja el authorization server endpoints
		@Bean
		public JwtTokenStore tokenStore() {
			return new JwtTokenStore(accessTokenConverter());
			
		}

		@Bean
		//toda la implenentacion del token jwt para traducir y decodificar y codificar los datos cuando se cree o se quiera mirar el token 
		public JwtAccessTokenConverter accessTokenConverter() {
			JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
			//damos una llaave especifica en clase con metodo estatico con llave setSingingKey es el que firma llave privada
			jwtAccessTokenConverter.setSigningKey(JwtTokenConfig.RSA_PRIVADA);
			jwtAccessTokenConverter.setVerifierKey(JwtTokenConfig.RSA_PUBLICA);
			return jwtAccessTokenConverter;
		}

}
