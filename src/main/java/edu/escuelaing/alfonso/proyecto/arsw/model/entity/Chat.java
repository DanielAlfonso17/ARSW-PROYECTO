package edu.escuelaing.alfonso.proyecto.arsw.model.entity;


import java.time.LocalDateTime;

import javax.persistence.Table;

@Table(name = "chats")
public class Chat {
	private String contenido;
	private String remitente;
	private String destinatario;
	private LocalDateTime hora = LocalDateTime.now();
	private MessageType type;
	
	public enum MessageType {
		ENTRO,
		ABANDONO,
		ESCRIBIENDO,
		CHAT
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public LocalDateTime getHoraDateTime() {
		return hora;
	}

	public void setHoraDateTime(LocalDateTime horaDateTime) {
		this.hora = horaDateTime;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
	
	

}
