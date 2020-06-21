package edu.escuelaing.alfonso.proyecto.arsw.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Producto;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Vendedor;
import edu.escuelaing.alfonso.proyecto.arsw.model.services.ProductoService;
import edu.escuelaing.alfonso.proyecto.arsw.model.services.UploadFileService;
import edu.escuelaing.alfonso.proyecto.arsw.model.services.VendedorService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/comprador")
public class CompradorRestController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired 
	private VendedorService vendedorService;
	
	
	@GetMapping("/productos")
	public List<Producto> consultarProductos(){
		return productoService.findAll();
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<?> consultarProductoId(@PathVariable Long id){
		Producto producto = null; 
		Map<String, Object> response = new HashMap<>();
		try {
			producto = productoService.findById(id);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al consultar producto");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(producto == null) {
			response.put("mensaje", "El producto: ".concat(id.toString().concat(" no existe en la base de datos!.")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Producto>(producto,HttpStatus.OK);
	}
	
	@GetMapping("/productos/listar/{nombre}")
	public List<Producto> consultarProductoNombre(@PathVariable String nombre) {
		return productoService.findByNombreContainsWord(nombre);
	}
	
	@GetMapping("/productos/listar/{valor1}/{valor2}")
	public List<Producto> consultarProductosRangoPrecio(@PathVariable Double valor1, @PathVariable Double valor2){
		return productoService.findByRangoPrecios(valor1, valor2);
	}
	
	@GetMapping("/productos/buscar/{precio}")
	public List<Producto> consultarProductoPrecio(@PathVariable Double precio) {
		return productoService.findByPrecio(precio);
	} 
	
	@GetMapping("/productos/vendedor/{id}")
	public List<Producto> consultarProductosVendedor(@PathVariable Long id){
		Vendedor vendedor = vendedorService.findById(id);
		return (List<Producto>) productoService.findByVendedor(vendedor);
	}
	
	@GetMapping("/vendedor/{id}")
	public Vendedor consultarVendedor(@PathVariable Long id) {
		return vendedorService.findById(id);
	}
	
	@GetMapping("/productos/page/{page}")
	public Page<Producto> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 4);
		return productoService.findAll(pageable);
	
	}
	
	
}
