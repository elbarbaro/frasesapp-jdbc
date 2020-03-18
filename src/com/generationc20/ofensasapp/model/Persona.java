package com.generationc20.ofensasapp.model;

import java.util.Date;

public class Persona {
	
	private int id;
	private int idPais;
	private String nombre;
	private int edad;
	private String estado;
	private Date fecha;
	
	public Persona() {}
	
	public Persona(int idPais, String nombre, int edad, String estado) {
		this.idPais = idPais;
		this.nombre = nombre;
		this.edad = edad;
		this.estado = estado;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdPais() {
		return idPais;
	}
	
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	
	public String getNombre() {
		return nombre;
	} 
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
