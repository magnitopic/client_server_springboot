package tienda.discos.controllers;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tienda.discos.utilidadesSetUp.ServicioSetUp;

@Controller
public class InicioController {
	
	@Autowired
	private ServicioSetUp servicioSetUp;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping()
	public String inicio() {
		
		servicioSetUp.prepararSetup();
		
		// vamos a consultar el idioma de usuario para dar un inicio u otro
		String idiomaActual = messageSource.getMessage("idioma", null, LocaleContextHolder.getLocale());
		System.out.println("idiomaActual: "+ idiomaActual);
		
		
		return "inicio";
	}
}
