package com.local.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {
	
	@Id
	private int id;
	private String nombre;
	@Column(name = "cant_municipios")
	private int cantMunicipios;
	@OneToMany(mappedBy = "departamento")
	private List<Persona> personas;
	
	public Departamento() {
		
	}

	public Departamento(int id, String nombre, int cantMunicipios, List<Persona> personas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantMunicipios = cantMunicipios;
		this.personas = personas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantMunicipios() {
		return cantMunicipios;
	}

	public void setCantMunicipios(int cantMunicipios) {
		this.cantMunicipios = cantMunicipios;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
}
