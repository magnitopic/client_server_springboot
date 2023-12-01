package tienda.discos.webservices;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tienda.discos.datos.serviciosWeb.InfoDiscos;
import tienda.discos.servicios.ServicioDiscos;

@Controller
@RestController
@RequestMapping("servicioWebDiscos/")
public class ServicioWebDiscos {
	
	// como pedir una bean de spring cuyo nombre de clase se usa en varios paquetes
	// ej.
	//webservices.ServiciosWEBPedidos
	//webservices.admin.ServiciosWEBPedidos
//	@Autowired
//	@Qualifier("servicioWebAdmin")
//	private ServicioWebDiscos servicioWebDiscos;
	
	@Autowired
	private ServicioDiscos servicioDiscos;
	
	@RequestMapping("obtenerDiscos")
	public InfoDiscos obtenerDiscos(
			@RequestParam(name = "nombre", defaultValue = "")String titulo,
			@RequestParam(name = "comienzo", defaultValue = "0") int comienzo,
			@RequestParam(name = "maxPrecio", defaultValue = "250") int maxPrecio,
			@RequestParam(name = "artista", defaultValue = "") String artista ){
		//return servicioDiscos.obtenerDiscosParaFormatJSON(titulo, comienzo);
		InfoDiscos info = new InfoDiscos();
		info.setDiscos(servicioDiscos.obtenerDiscosParaFormatJSON(titulo, comienzo, artista, maxPrecio));
		info.setTotalDiscos(servicioDiscos.obtenerTotalDiscos(titulo, artista, maxPrecio));
		info.setArtistas(servicioDiscos.obtenerArtistasDiscos());
		return info;
	}
	
	@RequestMapping("obtenerDiscoDetalles")
	public Map<String, Object> obtenerDiscosDetalles(@RequestParam("id") Integer id){
		return servicioDiscos.obtenerDetallesDisco(id);
	}
}
