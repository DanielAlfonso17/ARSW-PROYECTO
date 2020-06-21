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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.escuelaing.alfonso.proyecto.arsw.model.entity.Producto;
import edu.escuelaing.alfonso.proyecto.arsw.model.services.ProductoService;
import edu.escuelaing.alfonso.proyecto.arsw.model.services.UploadFileService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/vendedor")
public class VendedorRestController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired 
	private UploadFileService uploadService;
	
	
	@PostMapping("/productos")
	public ResponseEntity<?> create(@Valid @RequestBody Producto producto, BindingResult result) {
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

}
