package tienda.discos.servicios;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import tienda.discos.model.Disco;

public interface ServicioDiscos {
	void registrarDisco(Disco d);
	
	List<Disco> obtenerDiscos();
	
	void borrarDisco(int id);
	
	Disco obtenerDiscoPorId(int id);
	
	void gurdarCambioDisco(Disco d);

	Map<String, Object> obtenerDetallesDisco(int parseInt);

	List<Map<String, Object>> obtenerDiscosParaFormatJSON();
	
}
