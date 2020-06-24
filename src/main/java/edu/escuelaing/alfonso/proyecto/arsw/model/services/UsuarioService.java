package edu.escuelaing.alfonso.proyecto.arsw.model.services;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Usuario;

public interface UsuarioService {
	public Usuario findByUsername(String username);
}
