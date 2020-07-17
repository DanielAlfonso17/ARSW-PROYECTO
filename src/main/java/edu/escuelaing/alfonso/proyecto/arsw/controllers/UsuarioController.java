package edu.escuelaing.alfonso.proyecto.arsw.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.alfonso.proyecto.arsw.auth.LoginRequest;
import edu.escuelaing.alfonso.proyecto.arsw.auth.SingUpRequest;
import edu.escuelaing.alfonso.proyecto.arsw.auth.security.JwtResponse;
import edu.escuelaing.alfonso.proyecto.arsw.auth.security.JwtUtils;
import edu.escuelaing.alfonso.proyecto.arsw.auth.security.MessageResponse;
import edu.escuelaing.alfonso.proyecto.arsw.model.dao.CompradorDao;
import edu.escuelaing.alfonso.proyecto.arsw.model.dao.RolDao;
import edu.escuelaing.alfonso.proyecto.arsw.model.dao.UsuarioDao;
import edu.escuelaing.alfonso.proyecto.arsw.model.dao.VendedorDao;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Comprador;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Rol;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Usuario;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Vendedor;
import edu.escuelaing.alfonso.proyecto.arsw.model.services.UserDetailsImpl;


@CrossOrigin
@RestController
@RequestMapping("/auth")
public class UsuarioController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioDao userRepository;

	@Autowired
	RolDao rolDao;
	
	@Autowired
	CompradorDao compradorDao;
	
	@Autowired
	VendedorDao vendedorDao;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SingUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		// Create new user's account
		
		Usuario user = new Usuario(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()), 
							 signUpRequest.getNombre(), 
							 signUpRequest.getApellido());
		
		
		String roles = signUpRequest.getRol();
		if(roles.equals(null)) {
			new RuntimeException("Error, no existe el rol");
		}else {
			Rol rol = rolDao.findByNombre(roles);
			List<Rol> rolesUser = new ArrayList<>();
			rolesUser.add(rol);
			user.setRoles(rolesUser);
			if(roles.equals("ROLE_COMPRADOR")) {
				Comprador comprador = new Comprador(user.getNombre(), user.getApellido(), user.getEmail());
				compradorDao.save(comprador);
			}else if(roles.equals("ROLE_VENDEDOR")) {
				Vendedor vendedor = new Vendedor(user.getNombre(), user.getEmail());
				vendedorDao.save(vendedor);
			}
		}
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
