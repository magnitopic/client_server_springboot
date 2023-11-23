package tienda.discos.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
