package tienda.discos.webservices.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tienda.discos.servicios.ServicioPedidos;

@Controller(value = "servicioWebPedidosAdmin")
@RestController
@RequestMapping("admin/servicioWebPedidos/")
public class ServicioWebPedidos {

	@Autowired
	private ServicioPedidos servicioPedidos;
	
	@RequestMapping("actualizarEstadoPedido")
	public String actualizarEstadoPedido(@RequestParam("id") Integer id, String estado) {
		servicioPedidos.actualizarEstadoPedido(id, estado);
		return "ok";
	}
}
