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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;



@Entity
@Table(name = "compradores")
public class Comprador implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "No puede estar vacío")
	@Size(min = 5, max = 20, message = "El tamaño debe estar entre 5 y 20 caracteres")
	@Column(nullable = false)
	private String nombre;
	
	@NotEmpty(message = "No puede estar vacío")
	@Size(min = 5, max = 20, message = "El tamaño debe estar entre 5 y 20 caracteres")
	@Column(nullable = false)
	private String apellido;
	
	@NotEmpty(message = "No puede estar vacío")
	@Email(message = "No es una dirección de correo valida")
	@Column(nullable = false, unique = true)
	private String email;
	

	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	private String foto; 
	
	@JsonIgnoreProperties(value= {"cliente","hibernateLazyInitializer","handler"},allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comprador", cascade = CascadeType.ALL)
	private List<Factura> facturas;
	
	
	public Comprador() {
		this.facturas = new ArrayList<>();
	}
	
	public Comprador(String nombre, String apellido, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.facturas = new ArrayList<>();
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	private static final long serialVersionUID = 1L;

}
