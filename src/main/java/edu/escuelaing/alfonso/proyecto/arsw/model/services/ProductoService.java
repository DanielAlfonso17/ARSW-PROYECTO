package edu.escuelaing.alfonso.proyecto.arsw.model.services;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Producto;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Vendedor;

public interface ProductoService {
	public List<Producto> findAll();
	
	public Producto save(Producto producto);
	
	public void delete(Long id);
	
	public Producto findById(Long id);
	
	public Page<Producto> findAll(Pageable pageable);
	
	public Producto findByNombre(String nombre);
	
	public List<Producto> findByPrecio(Double precio);
	
	public List<Producto> findByNombreContainsWord(String palabra);
	
	public List<Producto> findByRangoPrecios(Double valor1, Double valor2);
	
	public List<Producto> findByVendedor(Vendedor vendedor);
	
}
