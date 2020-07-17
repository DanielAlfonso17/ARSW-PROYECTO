package edu.escuelaing.alfonso.proyecto.arsw.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "vendedores")
public class Vendedor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "No puede estar vacio")
	@Size(min=4, max=15, message = "El tamaño tiene que estar entre 4 y 15 caracteres")
	@Column(nullable = false)
	private String nombre;
	
	@NotEmpty(message = "No puede estar vacio")
	@Email(message = "No es una dirección de correo valida")
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	@JsonIgnoreProperties(value= {"vendedor","hibernateLazyInitializer","handler"},allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendedor",cascade = CascadeType.ALL)
	private List<Producto> productos;
	
	
	public Vendedor() {
		this.productos = new ArrayList<>();
	}
	
	public Vendedor(String nombre, String email) {
		this.nombre = nombre;
		this.email = email;
		this.productos = new ArrayList<>();
		
	}
	
	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	private static final long serialVersionUID = 1L;

}
