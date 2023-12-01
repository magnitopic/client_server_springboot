package tienda.discos.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.discos.model.Usuario;
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

	@RequestMapping("editarUsuario")
	public String editarUsuario(@RequestParam("id") Integer id, Model model) {
		Usuario u = servicioUsuarios.obtenerUserPorId(id);
		model.addAttribute("usuarioEditar", u);
		return "admin/usuario_edicion";
	}

	@RequestMapping("guardarCambiosUsuario")
	public String guardarCambiosUsuario(@ModelAttribute("usuarioEditar") Usuario usuarioEditar,
			BindingResult resultadoValidaciones, Model model) {
		if (resultadoValidaciones.hasErrors()) {
			return "admin/usuario_edicion";
		}
		servicioUsuarios.guardarCambiosUsuario(usuarioEditar);
		return "admin/usuario_edicion_ok";
	}
}
