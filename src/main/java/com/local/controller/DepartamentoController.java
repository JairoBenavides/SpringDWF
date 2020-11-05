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
public class DepartamentoController {
	
	@Autowired
	IDepartamento repo;
	
	@Autowired
	IPersona repoPersona;
	
	@GetMapping("Departamento/listar")
	public String listarDepartamentos(Model modelo) {
		List<Departamento> listaDepartamentos = (List<Departamento>) repo.findAll();
		modelo.addAttribute("departamentos", listaDepartamentos);
		return "listaDepartamentos";
	}
	
	@GetMapping("Departamento/nuevo")
	public String nuevoDepartamento(Model modelo) {
		return "nuevoDepartamento";
	}
	
	public String validarDatos(Departamento departamento){
		String mensaje = null;
		
		if (departamento.getNombre() != "") {
			if (departamento.getCantMunicipios() > 0) {
				mensaje = "exito";
			}
			else {
				mensaje = "La cantidad de municipios del departamento debe ser mayor a 0.";
			}
		}
		else {
			mensaje = "Por favor ingrese el nombre del departamento.";
		}
		
		return mensaje;
	}
	
	public Model agregarMensajeModelo(Model modelo, String icono, String mensaje, String severidad) {
		modelo.addAttribute("icono", icono);
		modelo.addAttribute("mensaje", mensaje);
		modelo.addAttribute("severidad", severidad);
		
		return modelo;
	}
	
	@PostMapping("Departamento/guardar")
	public String guardarDepartamento(@ModelAttribute Departamento departamento, Model modelo) {
		//Variables de mensaje
		String icono = "";
		String mensaje = "";
		String severidad = "";
		
		mensaje = validarDatos(departamento);
		
		if (mensaje == "exito") {
			icono = "fa fa-check";
			mensaje = "La información ha sido almacenada exitosamente.";
			severidad = "success";
			repo.save(departamento);
		}
		else {
			icono = "fa fa-exclamation-triangle";
			severidad = "error";
		}

		modelo = agregarMensajeModelo(modelo, icono, mensaje, severidad);
		return "nuevoDepartamento";
	}
	
	@GetMapping("Departamento/editar/{idDepartamento}")
	public String obtenerDepartamento(@PathVariable("idDepartamento") int idDepartamento, Model modelo) {
		Optional<Departamento> resultado = repo.findById(idDepartamento);
		if (resultado.isPresent()) {
			Departamento departamento = resultado.get();
			modelo.addAttribute("departamento", departamento);
		}
		
		return "editarDepartamento";
	}
	
	@PostMapping("Departamento/editar")
	public String editarDepartamento(@ModelAttribute Departamento departamento, Model modelo) {
		//Variables de mensaje
		String icono = "";
		String mensaje = "";
		String severidad = "";
		
		mensaje = validarDatos(departamento);
		
		if (mensaje == "exito") {
			icono = "fa fa-check";
			mensaje = "La información ha sido actualizada exitosamente.";
			severidad = "success";
			repo.save(departamento);
		}
		else {
			icono = "fa fa-exclamation-triangle";
			severidad = "error";
		}
		
		modelo = agregarMensajeModelo(modelo, icono, mensaje, severidad);
		return listarDepartamentos(modelo);
	}
	
	@GetMapping("Departamento/eliminar/{idDepartamento}")
	public String eliminarRegistro(@PathVariable("idDepartamento") int idDepartamento, Model modelo) {
		String icono = "fa fa-check";
		String mensaje = "La información ha sido eliminada exitosamente.";
		String severidad = "success";
		Optional<Departamento> resultado = repo.findById(idDepartamento);
		if (resultado.isPresent()) {
			Departamento departamento = resultado.get();
			List<Persona> personas = (List<Persona>) repoPersona.findAll();
			for(Persona p : personas) {
				if (p.getDepartamento().getId() == departamento.getId()) {
					repoPersona.delete(p);
				}
			}
			repo.delete(departamento);
		}
		
		modelo = agregarMensajeModelo(modelo, icono, mensaje, severidad);
		return listarDepartamentos(modelo);
	}
	
}
