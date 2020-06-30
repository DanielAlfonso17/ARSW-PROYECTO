package edu.escuelaing.alfonso.proyecto.arsw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Factura;
import edu.escuelaing.alfonso.proyecto.arsw.model.services.CompradorService;

@CrossOrigin
@RestController
@RequestMapping("/offerbuy")
public class FacturaRestController {
	
	@Autowired
	private CompradorService compradorService; 
	
	@GetMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Factura show(@PathVariable Long id) {
		return compradorService.findFacturaById(id);
	}
	
	@DeleteMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		compradorService.deleteFacturaById(id);
	}
	
	@PostMapping("/facturas")
	@ResponseStatus(HttpStatus.CREATED)
	public Factura crear(@RequestBody Factura factura) {
		return compradorService.saveFactura(factura);
	}

}
