package tienda.discos.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tienda.discos.servicios.ServicioPedidos;
import tienda.discos.servicios.ServicioUsuarios;

@Controller
@RequestMapping("admin/")
public class UsuariosController {

	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("obtenerUsuarios")
	public String obtenerUsuarios(Model model) {
		model.addAttribute("usuarios", servicioUsuarios.obtenerUsuarios());
		return "admin/usuarios";
	}
	
}
