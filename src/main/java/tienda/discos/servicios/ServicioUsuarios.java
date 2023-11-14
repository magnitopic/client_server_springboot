package tienda.discos.servicios;

import tienda.discos.model.Usuario;

public interface ServicioUsuarios {

	
	void registarUsuario(Usuario u);
	
	Usuario obtenerUserPorMailYpass(String email, String pass);
}
