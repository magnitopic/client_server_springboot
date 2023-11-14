package tienda.discos.controllers.imagen;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.discos.servicios.ServicioDiscos;

@Controller
public class MostrarImagenProducto {

	@Autowired
	private ServicioDiscos servicioDiscos;
	
	@RequestMapping("mostrar_imagen")
	public void mostrar_imagen(@RequestParam("id") Integer id, HttpServletResponse resp) throws IOException {
		byte[] info = servicioDiscos.obtenerDiscoPorId(id).getImagenPortada();
		if (info == null) {
			return;
		}
		resp.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		resp.getOutputStream().write(info);
		resp.getOutputStream().close(); 
	}
}
