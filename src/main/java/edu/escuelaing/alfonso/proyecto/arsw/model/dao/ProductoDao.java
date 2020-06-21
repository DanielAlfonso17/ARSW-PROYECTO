package edu.escuelaing.alfonso.proyecto.arsw.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Producto;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Vendedor;

public interface ProductoDao extends JpaRepository<Producto, Long> {
	
	public Producto findByNombre(String nombre);	
	
	public List<Producto> findByNombreContainingIgnoreCase(String term);
	
	@Query("select p from Producto p where p.precio > ?1 and p.precio < ?2")
	public List<Producto>  findByRangoPrecio(Double valor1, Double valor2);
	
	public List<Producto> findByPrecio(Double precio);
	
	@Query("select p from Producto p where p.vendedor = ?1")
	public List<Producto> findProductosById(Vendedor vendedor);
	

	
	
}
