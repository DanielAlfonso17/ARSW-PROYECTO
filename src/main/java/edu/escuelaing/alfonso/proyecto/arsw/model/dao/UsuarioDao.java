package edu.escuelaing.alfonso.proyecto.arsw.model.dao;

import org.springframework.data.repository.CrudRepository;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {
	public Usuario findByUsername(String username);
	
	
	

}
