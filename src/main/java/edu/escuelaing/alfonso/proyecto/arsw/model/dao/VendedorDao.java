package edu.escuelaing.alfonso.proyecto.arsw.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Vendedor;

public interface VendedorDao extends JpaRepository<Vendedor, Long> {
	public Vendedor findByNombre(String nombre);
	
	public Vendedor findByEmail(String email);
	
	public List<Vendedor> findByNombreContainingIgnoreCase(String term);
	


}
