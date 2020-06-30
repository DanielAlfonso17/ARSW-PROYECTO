package edu.escuelaing.alfonso.proyecto.arsw.model.services;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Comprador;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Factura;

public interface CompradorService {
	public Comprador save(Comprador comprador);
	
	public void delete(Long id);
	
	public Comprador findById(Long id);
	
	public Factura findFacturaById(Long id);
	
	public Factura saveFactura(Factura factura);
	
	public void deleteFacturaById(Long id);
}
