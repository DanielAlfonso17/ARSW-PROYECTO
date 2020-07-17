package edu.escuelaing.alfonso.proyecto.arsw.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/auth/test")
public class AccessTestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public content";
	}
	
	@GetMapping("/comprador")
	@PreAuthorize("hasRole('ROLE_COMPRADOR')")
	public String compradorAccess() {
		return "Comprador Content.";
	}
	
	@GetMapping("/vendedor")
	@PreAuthorize("hasRole('ROLE_VENDEDOR')")
	public String vendedorAccess() {
		return "Vendedor Content.";
	}
	

}
