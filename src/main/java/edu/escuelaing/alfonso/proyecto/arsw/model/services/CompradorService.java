package edu.escuelaing.alfonso.proyecto.arsw.model.services;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Comprador;

public interface CompradorService {
	public Comprador save(Comprador comprador);
	
	public void delete(Long id);
	
	public Comprador findById(Long id);
}
