package tienda.discos.servicios;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import tienda.discos.model.Disco;

public interface ServicioDiscos {
	void registrarDisco(Disco d);
	
	List<Disco> obtenerDiscos();

	List<String> obtenerArtistasDiscos();
	
	List<Disco> obtenerDiscosPorNombre(String nombre);
	
	List<Disco> obtenerDiscosPorNombreComienzoFin(String nombre, int comienzo, int resultadosPorPagina);
	
	int obtenerTotalDiscos(String nombre, String artista, int maxPrecio);

	int obtenerTotalDiscos(String nombre);
	
	void borrarDisco(int id);
	
	Disco obtenerDiscoPorId(int id);
	
	void gurdarCambioDisco(Disco d);
	
	
	
	// opreaciones para web
	Map<String, Object> obtenerDetallesDisco(int idDisco);

	List<Map<String, Object>> obtenerDiscosParaFormatJSON(String nombre, int comienzo, String artista, int maxPrecio);
	
	List<Map<String, Object>> obtener(String nombre, int comienzo, int resultadosPorPagina);
}
