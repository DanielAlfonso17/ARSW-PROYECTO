package edu.escuelaing.alfonso.proyecto.arsw.model.services;

import java.util.List;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Vendedor;

public interface VendedorService {
	public List<Vendedor> findAll();
		
	public Vendedor findById(Long id);
	
	public Vendedor findByNombre(String nombre);
	


}
