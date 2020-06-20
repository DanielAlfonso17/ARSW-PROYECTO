package edu.escuelaing.alfonso.proyecto.arsw.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long> {
	
	public Producto findByNombre(String nombre);	
	
	public List<Producto> findByNombreContainingIgnoreCase(String term);

	
	
}
