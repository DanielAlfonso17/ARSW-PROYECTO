package edu.escuelaing.alfonso.proyecto.arsw.model.dao;

import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Vendedor;

public interface VendedorDao extends CrudRepository<Vendedor, Long> {
	public Vendedor findByNombre(String nombre);
	
	public Vendedor findByEmail(String email);
	
	


}
