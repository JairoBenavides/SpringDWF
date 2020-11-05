package com.local.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.local.entidades.Departamento;
import com.local.entidades.Persona;
import com.local.repositorios.IDepartamento;
import com.local.repositorios.IPersona;

@Controller
public class PersonaController {
	
	@Autowired
	IPersona repo;
	
	@Autowired
	IDepartamento repoDepto;
	
	@GetMapping("Persona/listar")
	public String listarPersonas(Model modelo) {
		List<Persona> listaPersonas = (List<Persona>) repo.findAll();
		modelo.addAttribute("personas", listaPersonas);
		return "listaPersonas";
	}
	
	@GetMapping("Persona/nuevo")
	public String nuevaPersona(Model modelo) {
		List<Departamento> listaDepartamentos = (List<Departamento>) repoDepto.findAll();
		modelo.addAttribute("departamentos", listaDepartamentos);
		return "nuevaPersona";
	}
	
	public String validarDatos(Persona persona){
		String mensaje = null;
		
		if (persona.getNombre() != "") {
			if (persona.getApellido() != "") {
				if (persona.getEdad() > 0 && persona.getEdad() < 100) {
					mensaje = "exito";
				}
				else {
					mensaje = "La edad de la perseona debe ser mayor a 0.";
				}
			}
			else {
				mensaje = "Por favor ingrese el apellido de la persona.";
			}
		}
		else {
			mensaje = "Por favor ingrese el nombre de la persona.";
		}
		
		return mensaje;
	}
	
	public Model agregarMensajeModelo(Model modelo, String icono, String mensaje, String severidad) {
		modelo.addAttribute("icono", icono);
		modelo.addAttribute("mensaje", mensaje);
		modelo.addAttribute("severidad", severidad);
		
		return modelo;
	}
	
	@PostMapping("Persona/guardar")
	public String guardarPersona(@ModelAttribute Persona persona, Model modelo) {
		//Variables de mensaje
		String icono = "";
		String mensaje = "";
		String severidad = "";
		
		mensaje = validarDatos(persona);
		
		if (mensaje == "exito") {
			icono = "fa fa-check";
			mensaje = "La información ha sido almacenada exitosamente.";
			severidad = "success";
			repo.save(persona);
		}
		else {
			icono = "fa fa-exclamation-triangle";
			severidad = "error";
		}

		modelo = agregarMensajeModelo(modelo, icono, mensaje, severidad);
		return nuevaPersona(modelo);
	}
	
	@GetMapping("Persona/editar/{idPersona}")
	public String obtenerPersona(@PathVariable("idPersona") int idPersona, Model modelo) {
		List<Departamento> listaDepartamentos = (List<Departamento>)repoDepto.findAll();
		
		Optional<Persona> resultado = repo.findById(idPersona);
		if (resultado.isPresent()) {
			Persona persona = resultado.get();
			modelo.addAttribute("persona", persona);
		}
		
		modelo.addAttribute("departamentos", listaDepartamentos);
		return "editarPersona";
	}
	
	@PostMapping("Persona/editar")
	public String editarPersona(@ModelAttribute Persona persona, Model modelo) {
		//Variables de mensaje
		String icono = "";
		String mensaje = "";
		String severidad = "";
		
		mensaje = validarDatos(persona);
		
		if (mensaje == "exito") {
			icono = "fa fa-check";
			mensaje = "La información ha sido actualizada exitosamente.";
			severidad = "success";
			repo.save(persona);
		}
		else {
			icono = "fa fa-exclamation-triangle";
			severidad = "error";
		}
		
		modelo = agregarMensajeModelo(modelo, icono, mensaje, severidad);
		return listarPersonas(modelo);
	}
	
	@GetMapping("Persona/eliminar/{idPersona}")
	public String eliminarRegistro(@PathVariable("idPersona") int idPersona, Model modelo) {
		String icono = "fa fa-check";
		String mensaje = "La información ha sido eliminada exitosamente.";
		String severidad = "success";
		Optional<Persona> resultado = repo.findById(idPersona);
		if (resultado.isPresent()) {
			Persona persona = resultado.get();
			repo.delete(persona);
		}
		
		modelo = agregarMensajeModelo(modelo, icono, mensaje, severidad);
		return listarPersonas(modelo);
	}
	
}
