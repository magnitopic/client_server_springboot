package tienda.discos.controllers.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.discos.model.Disco;
import tienda.discos.servicios.ServicioDiscos;
import tienda.discos.servicios.ServicioGeneros;
import tienda.discos.utilidades.GestorArchivos;

@Controller
@RequestMapping("admin/")
public class DiscosController {
	
	@Autowired
	private ServicioDiscos servicioDiscos;
	
	@Autowired
	private ServicioGeneros servicioGeneros;
	
	@RequestMapping("obtenerDiscos")
	public String obtenerDiscos(@RequestParam(name = "nombre", defaultValue = "") String nombre,Model model) {
		model.addAttribute("discos", servicioDiscos.obtenerDiscosPorNombre(nombre));
		model.addAttribute("nombre", nombre);
		return "admin/discos";
	}

	@RequestMapping("registrarDisco")
	public String registrarDisco(Model model) {
		Disco d = new Disco();
		d.setPrecio(21.99);
		
		model.addAttribute("nuevoDisco", d);
		model.addAttribute("generos", servicioGeneros.obtenerGeneros());

		return "admin/discos_registro";
	}

	@RequestMapping("guardarDisco")
	public String guardarDisco(Disco nuevoDisco, Model model) {
		System.out.println("id de genero seleccionado: "+ nuevoDisco.getIdGenero());
		servicioDiscos.registrarDisco(nuevoDisco);
		return "admin/discos_registro_ok";
	}
	
	@RequestMapping("borrarDisco")
	public String borrarDisco(@RequestParam("id") Integer id, Model model)  {
		servicioDiscos.borrarDisco(id);
		return obtenerDiscos("", model);
	}
	
	@RequestMapping("editarDisco")
	public String editarDisco(@RequestParam("id") Integer id, Model model)  {
		Disco d = servicioDiscos.obtenerDiscoPorId(id);
		d.setIdGenero(d.getGenero().getId());
		model.addAttribute("discoEditar", d);
		model.addAttribute("generos", servicioGeneros.obtenerGeneros());
		return ("admin/discos_editar");
	}
	
	
	@RequestMapping("guardarCambiosDisco")
	public String guardarCambiosDisco(Disco disco, Model model) {
		servicioDiscos.gurdarCambioDisco(disco);
		return obtenerDiscos("", model);
	}
}
