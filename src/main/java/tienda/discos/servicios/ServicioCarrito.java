package tienda.discos.servicios;

import java.util.List;
import java.util.Map;


public interface ServicioCarrito {
	
	void agreggarProducto(int idUsuario, int idProducto, int cantidad);
	void actualizarProductoCarrito(int idUsuario, int idProducto, int cantidad);
	void borrarProductoCarrito(int idUsuario, int idProducto);
	
	// Operaciones Ajax
	
	List<Map<String, Object>> obtenerProductosCarrito(int idUsuario);
}
