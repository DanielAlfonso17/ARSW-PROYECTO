package edu.escuelaing.alfonso.proyecto.arsw.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.alfonso.proyecto.arsw.model.dao.CompradorDao;
import edu.escuelaing.alfonso.proyecto.arsw.model.dao.FacturaDao;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Comprador;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Factura;

@Service
public class FacturaServiceImpl implements FacturaService{

	@Autowired
	FacturaDao facturaDao;
	
	@Autowired 
	CompradorDao compradorDao;
	
	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Factura saveFactura(Factura factura) {
		return facturaDao.save(factura);
	}

	@Override
	@Transactional
	public void deleteFacturaById(Long id) {
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Factura> findFacturaByComprador(Comprador comprador) {
		return (List<Factura>) facturaDao.findByComprador(comprador);
	}

}
