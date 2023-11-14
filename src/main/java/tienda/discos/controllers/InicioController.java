package tienda.discos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tienda.discos.utilidadesSetUp.ServicioSetUp;

@Controller
public class InicioController {
	
	@Autowired
	private ServicioSetUp servicioSetUp;
	
	@RequestMapping()
	public String inicio() {
		
		servicioSetUp.prepararSetup();
		
		return "inicio";
	}
}
