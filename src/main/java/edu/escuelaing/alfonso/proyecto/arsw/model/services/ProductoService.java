package edu.escuelaing.alfonso.proyecto.arsw.model.services;

import java.util.List;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Producto;

public interface ProductoService {
	public List<Producto> findAll();
	
	public Producto save(Producto producto);
	
	public void delete(Long id);
	
	public Producto findById(Long id);
	
	public List<Producto> findByNombreContainingIgnoreCase(String term);
	
}
