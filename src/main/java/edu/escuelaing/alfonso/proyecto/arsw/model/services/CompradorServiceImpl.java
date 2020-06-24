package edu.escuelaing.alfonso.proyecto.arsw.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.alfonso.proyecto.arsw.model.dao.CompradorDao;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Comprador;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Producto;

@Service
public class CompradorServiceImpl implements CompradorService {

	@Autowired
	private CompradorDao compradorDao;
	
	@Override
	@Transactional
	public Comprador save(Comprador comprador) {
		return compradorDao.save(comprador);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		compradorDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Comprador findById(Long id) {
		return compradorDao.findById(id).orElse(null);
	}

}
