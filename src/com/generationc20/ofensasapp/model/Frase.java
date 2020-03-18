package com.generationc20.ofensasapp.model;

import java.util.Date;

public class Frase {
	
	private int id;
	private String contenido;
	private String significado;
	private int ofensividad;
	private String contexto;
	private Date fecha;
	
	public Frase() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContenido() {
		return contenido;
	}
	
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	public String getSignificado() {
		return significado;
	}
	
	public void setSignificado(String significado) {
		this.significado = significado;
	}
	
	public int getOfensividad() {
		return ofensividad;
	}
	
	public void setOfensividad(int ofensividad) {
		this.ofensividad = ofensividad;
	} 
	
	public String getContexto() {
		return contexto;
	}
	
	public void setContexto(String contexto) {
		this.contexto = contexto;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}

