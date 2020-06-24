package edu.escuelaing.alfonso.proyecto.arsw.model.services;

import java.util.List;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Comprador;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Factura;

public interface FacturaService {
	public Factura findFacturaById(Long id);
	
	public Factura saveFactura(Factura factura);
	
	public void deleteFacturaById(Long id);
	
	public List<Factura> findFacturaByComprador(Comprador comprador);

}
