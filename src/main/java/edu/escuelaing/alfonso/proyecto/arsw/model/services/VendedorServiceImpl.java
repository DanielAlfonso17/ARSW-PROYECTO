package edu.escuelaing.alfonso.proyecto.arsw.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.alfonso.proyecto.arsw.model.dao.VendedorDao;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Vendedor;

@Service
public class VendedorServiceImpl implements VendedorService{

	@Autowired
	private VendedorDao vendedorDao;
	@Override
	public List<Vendedor> findAll() {
		return (List<Vendedor>) vendedorDao.findAll();
		
	}

	@Override
	public Vendedor findById(Long id) {
		return vendedorDao.findById(id).orElse(null);
		
	}

	@Override
	public Vendedor findByNombre(String nombre) {
		return vendedorDao.findByNombre(nombre);
	}

	@Override
	public Vendedor findByEmail(String email) {
		return vendedorDao.findByEmail(email);
	}

	@Override
	public List<Vendedor> findByWords(String nombre) {
		return vendedorDao.findByNombreContainingIgnoreCase(nombre);
	}

}
