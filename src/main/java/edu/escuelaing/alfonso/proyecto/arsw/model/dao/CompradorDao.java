package edu.escuelaing.alfonso.proyecto.arsw.model.dao;

import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Comprador;

public interface CompradorDao extends CrudRepository<Comprador,Long> {
	public Comprador findByEmail(String email);
	
	

}
