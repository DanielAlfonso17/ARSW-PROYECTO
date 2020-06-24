package edu.escuelaing.alfonso.proyecto.arsw.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Producto;
import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Vendedor;
import edu.escuelaing.alfonso.proyecto.arsw.model.services.ProductoService;
import edu.escuelaing.alfonso.proyecto.arsw.model.services.UploadFileService;
import edu.escuelaing.alfonso.proyecto.arsw.model.services.VendedorService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/vendedor")
public class VendedorRestController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired 
	private UploadFileService uploadService;
	
	@Autowired
	private VendedorService vendedorService;
	
	
	@PostMapping("/productos/{id}")
	public ResponseEntity<?> create(@Valid @RequestBody Producto producto, BindingResult result,@PathVariable Long id) {
		Producto productoCreado = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()){
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " +err.getDefaultMessage()).collect(Collectors.toList());
					 
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			Vendedor vendedor = vendedorService.findById(id);
			if(vendedor == null) {
				response.put("mensaje", "Error, el vendedor no existe!");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
			}
			producto.setVendedor(vendedor);
			productoCreado = productoService.save(producto);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido creado con exito");
		response.put("producto",productoCreado);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/productos/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		Producto producto = productoService.findById(id);
		if(!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.guardar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del producto!");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			String nombreFotoAnterior = producto.getFoto();
			uploadService.eliminar(nombreFotoAnterior);

			producto.setFoto(nombreArchivo);
			productoService.save(producto);
			
			response.put("producto", producto);
			response.put("mensaje", "Ha subido correctamente la imagen: " + nombreArchivo);
		}
		
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	
	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Producto producto, BindingResult result,@PathVariable Long id) {
		Producto productoActual = productoService.findById(id);
		Producto productoUpdate = null;		
		Map<String,Object> response = new HashMap<>();		
		if(result.hasErrors()){
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " +err.getDefaultMessage()).collect(Collectors.toList());
					 
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}		
		if(productoActual == null) {
			response.put("mensaje", "Error no se pudo editar, producto ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}				
		try {
			productoActual.setNombre(producto.getNombre());
			productoActual.setPrecio(producto.getPrecio());
			productoActual.setDescripcion(producto.getDescripcion());
			productoActual.setFechaCreacion(producto.getFechaCreacion());
			productoUpdate = productoService.save(productoActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido actualizado con exito");
		response.put("producto",productoUpdate);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/productos/vendedor/{id}")
	public List<Producto> consultarProductosVendedor(@PathVariable Long id){
		Vendedor vendedor = vendedorService.findById(id);
		return (List<Producto>) productoService.findByVendedor(vendedor);
	}
	
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Producto producto = productoService.findById(id);
			String nombreFotoAnterior = producto.getFoto();
			uploadService.eliminar(nombreFotoAnterior);
			
			productoService.delete(id);	
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar producto en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje","El producto ha sido eliminado con exito!" );
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}
}
