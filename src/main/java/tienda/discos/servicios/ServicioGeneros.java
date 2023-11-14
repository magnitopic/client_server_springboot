package tienda.discos.servicios;

import java.util.List;
import java.util.Map;

import tienda.discos.model.Genero;

public interface ServicioGeneros {
	List<Genero> obtenerGeneros();
	
	Map<String, String> obtenerGenerosParaDesplegable();
	
}
