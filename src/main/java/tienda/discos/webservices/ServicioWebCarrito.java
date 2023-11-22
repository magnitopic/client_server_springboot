package tienda.discos.webservices;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tienda.discos.model.Usuario;
import tienda.discos.servicios.ServicioCarrito;


@Controller
@RestController
@RequestMapping("servicioWebCarrito/")
public class ServicioWebCarrito {
	
	@Autowired
	private ServicioCarrito servicioCarrito;
	
	@RequestMapping("agregarDisco")
	public String agregarDisco(String id, String cantidad, HttpServletRequest req) {
		String respuesta = "";
		if(req.getSession().getAttribute("usuario_identificado") != null) {
			//esto es que el usuario se identifico correctamente
			Usuario u = (Usuario)req.getSession().getAttribute("usuario_identificado");
			respuesta = " agregar producto de id: " + id + " cantidad: " + cantidad + " " +
					" al usuario de id: " + u.getId();	
			servicioCarrito.agreggarProducto(
					u.getId(), 
					Integer.parseInt(id), 
					Integer.parseInt(cantidad));
		}else {
			respuesta = "usuario no identificado, identificate para poder comprar productos";
		}
		return respuesta;
	}

	@RequestMapping("obtenerProductosCarrito")
	public List<Map<String, Object>> obtenerProductosCarrito(HttpServletRequest req) throws Exception{
		if (req.getSession().getAttribute("usuario_identificado") != null){
			int id = ((Usuario)req.getSession().getAttribute("usuario_identificado")).getId();
			return servicioCarrito.obtenerProductosCarrito(id);
		}else { 
			throw new Exception("** USUARIO NO IDENTIFICADO **");
		}
	}
	
	@RequestMapping("borrarProducto")
	public String borrarProducto(@RequestParam("id") int id, HttpServletRequest req) {
		servicioCarrito.borrarProductoCarrito(
				((Usuario)req.getSession().getAttribute("usuario_identificado")).getId(), id);
		return "ok";
	}
}
