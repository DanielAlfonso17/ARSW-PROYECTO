package edu.escuelaing.alfonso.proyecto.arsw.auth;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SingUpRequest {
	@NotNull
    @Size(min = 3, max = 20)
    private String username;
 
    @NotNull
    @Size(max = 50)
    @Email
    private String email;
    
    
    private String rol;
    
    @NotNull
    @Size(min = 6, max = 40)
    private String password;
    
    @NotNull
    @Size(min = 3, max=20)
    private String nombre;
    
    @NotNull
    @Size(min = 3, max=20)
    private String apellido;
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRol() {
      return this.rol;
    }
    
    public void setRole(String rol) {
      this.rol = rol;
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
    
    
}
