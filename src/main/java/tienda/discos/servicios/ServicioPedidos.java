package tienda.discos.servicios;

import java.util.List;

import tienda.discos.datos.serviciosWeb.ResumenPedido;
import tienda.discos.model.Pedido;




public interface ServicioPedidos {
	
	// gestion en administracion
	List<Pedido> obtenerPedidos();
	Pedido obtenerPedidoPorId(int idPedido);
	void actualizarEstadoPedido(int idPedido, String estado);

	// metodos para Ajax
	void procesarPaso1(String nombre, String direccion, String provincia, int idUsuario);
	void procesarPaso2(String titular, String numero, String tipoTarjeta, int idUsuario);
	void procesarPaso3(String regalo, String observaciones, int idUsuario);
	ResumenPedido obtenerResumenDelPedido(int idUsuario);
	void confirmarPedido(int idUsuario);
	List<Pedido> obtenerPedidosDeCliente(int idUsuario);
}
