package tienda.discos.controllers.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.discos.constantes.EstadosPedido;
import tienda.discos.model.Pedido;
import tienda.discos.servicios.ServicioPedidos;

@Controller
@RequestMapping("admin/")
public class PedidosController {
	
	@Autowired
	private ServicioPedidos servicioPedidos;
	
	@RequestMapping("obtenerPedidos")
	public String obtenerPedidos(Model model) {
		model.addAttribute("pedidos", servicioPedidos.obtenerPedidos());
		return "admin/pedidos";
	}
	
	@RequestMapping("verDetallesPedido")
	public String verDetallesPedido(@RequestParam("id") Integer id, Model model) {
		Pedido p = servicioPedidos.obtenerPedidoPorId(id);
		model.addAttribute("pedido",p);
		
		//vamos a darle los valores del desplegable de estado de pedido
		Map<String, String> estados = new HashMap<String, String>();
		estados.put(EstadosPedido.TERMINADO, "finalizado por el usuario");
		estados.put(EstadosPedido.LISTO_PARA_ENVIAR, "listo para ser recogido por la empresa de mensajeria");
		estados.put(EstadosPedido.RECIBIDO_POR_EL_CLIENTE, "el cliente recibio correctamente el pedido");

		model.addAttribute("estados", estados);
		
		return "admin/pedidos_detalle";
	}
}
