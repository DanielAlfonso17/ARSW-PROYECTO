package edu.escuelaing.alfonso.proyecto.arsw.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Rol;

public interface RolDao extends JpaRepository<Rol, Long> {
	public Rol findByNombre(String nombre);
	

}
