package tienda.discos.servicios;

import java.util.List;

import tienda.discos.model.Usuario;

public interface ServicioUsuarios {

	
	void registarUsuario(Usuario u);
	
	Usuario obtenerUserPorMailYpass(String email, String pass);
	Usuario obtenerUserPorId(int id);
	List<Usuario> obtenerUsuarios();
	void actualizarDatos(Integer id, String nombreUsuario, String pass);
}
