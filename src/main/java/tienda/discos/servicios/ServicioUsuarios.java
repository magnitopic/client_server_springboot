package tienda.discos.servicios;

import java.util.List;
import java.util.Map;

import tienda.discos.model.Usuario;

public interface ServicioUsuarios {

	
	void registarUsuario(Usuario u);
	
	Usuario obtenerUserPorMailYpass(String email, String pass);
	Usuario obtenerUserPorId(int id);
	List<Usuario> obtenerUsuarios();
	void actualizarDatos(Integer id, String nombreUsuario, String pass, String telefono, String pais);
	
	// metodos web
	Map<String, Object> nativeObtenerUserPorId(int id);

	void guardarCambiosUsuario(Usuario usuarioEditar);
}
