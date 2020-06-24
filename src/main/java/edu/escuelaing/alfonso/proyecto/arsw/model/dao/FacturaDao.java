package edu.escuelaing.alfonso.proyecto.arsw.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Comprador;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Factura;

public interface FacturaDao extends CrudRepository<Factura, Long>{
	@Query("select f from Factura f where f.comprador= ?1")
	public List<Factura> findByComprador(Comprador comprador);
}
