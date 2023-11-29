package tienda.discos.controllers.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.discos.model.Genero;
import tienda.discos.servicios.ServicioGeneros;

@Controller
@RequestMapping("admin/")
public class GenerosController {

	@Autowired
	private ServicioGeneros servicioGeneros;
	
	@RequestMapping("obtenerGeneros")
	public String obtenerGeneros(Model model) {
		model.addAttribute("generos", servicioGeneros.obtenerGeneros());
		return "admin/generos";
	}
	
	@RequestMapping("registrarGenero")
	public String registarGenero(Model model) {
		Genero g = new Genero();
		model.addAttribute("nuevoGenero", g);
		return "admin/genero_registro";
	}
	
	@RequestMapping("guardarGenero")
	public String guardarGenero(@ModelAttribute("nuevoGenero") @Valid Genero nuevoGenero , BindingResult resultadoValidaciones, Model model) {
		if (resultadoValidaciones.hasErrors()) {
			return "admin/genero_registro";
		}
		servicioGeneros.registrarGenero(nuevoGenero);
		System.out.println(nuevoGenero.getNombre());
		return "admin/genero_registro_ok";
	}

	@RequestMapping("editarGenero")
	public String editarGenero(@RequestParam("id") Integer id ,Model model) {
		Genero g = servicioGeneros.obtenerGeneroPorId(id);
		model.addAttribute("generoEditar", g);
		return "admin/genero_edicion";
	}

	@RequestMapping("guardarCambiosGenero")
	public String guardarCambiosGenero(@ModelAttribute("generoEditar") @Valid Genero generoEditar , BindingResult resultadoValidaciones, Model model) {
		if (resultadoValidaciones.hasErrors()) {
			return "admin/genero_edicion";
		}
		servicioGeneros.guardarCambiosGenero(generoEditar);
		return "admin/genero_edicion_ok";
	}

	@RequestMapping("borrarGenero")
	public String borrarGenero(@RequestParam("id") Integer id, Model model)  {
		servicioGeneros.eliminarGenero(id);
		return "redirect:obtenerGeneros";
	}
}
