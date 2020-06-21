package edu.escuelaing.alfonso.proyecto.arsw.model.services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.escuelaing.alfonso.proyecto.arsw.model.dao.ProductoDao;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Producto;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Vendedor;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly =  true)
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	public Producto findByNombre(String nombre) {
		return productoDao.findByNombre(nombre);
	}

	@Override
	public List<Producto> findByNombreContainsWord(String palabra) {
		return (List<Producto>) productoDao.findByNombreContainingIgnoreCase(palabra);
	}

	@Override
	public List<Producto> findByRangoPrecios(Double valor1, Double valor2) {
		return (List<Producto>) productoDao.findByRangoPrecio(valor1, valor2);
	}

	@Override
	public List<Producto> findByPrecio(Double precio) {
		return (List<Producto>) productoDao.findByPrecio(precio);
	}


	@Override
	public List<Producto> findByVendedor(Vendedor vendedor) {
		return (List<Producto>) productoDao.findProductosById(vendedor);
	}

	@Override
	public Page<Producto> findAll(Pageable pageable) {
		return productoDao.findAll(pageable);
	}
}
