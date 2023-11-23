package tienda.discos.webservices;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

import org.omg.CORBA.ULongLongSeqHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import tienda.discos.datos.serviciosWeb.ResumenPedido;
import tienda.discos.model.Pedido;
import tienda.discos.model.Usuario;
import tienda.discos.servicios.ServicioPedidos;

@Controller
@RestController
@RequestMapping("servicioWebPedidos/")
public class ServicioWebPedidos {

	@Autowired
	private ServicioPedidos servicioPedidos;

	@RequestMapping("paso1")
	public ResponseEntity<String> paso1(String nombre, String direccion, String provincia, HttpServletRequest req) {
		String resp = "";
		Usuario u = (Usuario) req.getSession().getAttribute("usuario_identificado");
		servicioPedidos.procesarPaso1(nombre, direccion, provincia, u.getId());
		resp = "ok";
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

	@RequestMapping("paso2")
	public ResponseEntity<String> paso2(String tarjeta, String numero, String titular, HttpServletRequest req) {
		String resp = "";
		Usuario u = (Usuario) req.getSession().getAttribute("usuario_identificado");
		servicioPedidos.procesarPaso2(titular, numero, tarjeta, u.getId());
		resp = "ok";
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

	@RequestMapping("paso3")
	public ResumenPedido paso3(String regalo, String observaciones, HttpServletRequest req) {
		Usuario u = (Usuario) req.getSession().getAttribute("usuario_identificado");
		servicioPedidos.procesarPaso3(regalo, observaciones, u.getId());
		ResumenPedido resumen = servicioPedidos.obtenerResumenDelPedido(u.getId());
		return resumen;
	}

	@RequestMapping("FinalPedido")
	public ResponseEntity<String> FinalPedido(HttpServletRequest req) {
		String resp = "";
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario_identificado");
		servicioPedidos.confirmarPedido(usuario.getId());
		resp = "pedido completado";
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

	@RequestMapping("obtenerPedidos")
	public List<Pedido> obtenerPedidos(HttpServletRequest req) throws Exception {
		if (req.getSession().getAttribute("usuario_identificado") != null) {
			int id = ((Usuario) req.getSession().getAttribute("usuario_identificado")).getId();
			return servicioPedidos.obtenerPedidosDeCliente(id);
		} else {
			throw new Exception("** USUARIO NO IDENTIFICADO **");
		}
	}
}
