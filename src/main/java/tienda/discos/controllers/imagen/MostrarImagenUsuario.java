package tienda.discos.controllers.imagen;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.discos.servicios.ServicioDiscos;
import tienda.discos.servicios.ServicioUsuarios;

@Controller
public class MostrarImagenUsuario {
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("mostrar_imagen_user")
	public void mostrar_imagen(@RequestParam("id") Integer id, HttpServletResponse resp) throws IOException {
		System.out.println("Yes");
		byte[] info = servicioUsuarios.obtenerUserPorId(id).getAvatar();
		if (info == null) {
			return;
		}
		resp.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		resp.getOutputStream().write(info);
		resp.getOutputStream().close(); 
	}
}
