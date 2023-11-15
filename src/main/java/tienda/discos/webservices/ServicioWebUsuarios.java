package tienda.discos.webservices;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import tienda.discos.model.Usuario;
import tienda.discos.servicios.ServicioUsuarios;


@Controller
@RestController
@RequestMapping("servicioWebUsuarios/")
public class ServicioWebUsuarios {
	
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	
	
	@RequestMapping("registrarUsuario")
	public String registrarUsuario(@RequestParam Map<String, Object> formData,
									MultipartHttpServletRequest req){
		System.out.println("recibido formData: " + formData);
		System.out.println("recibido foto: " + req.getFile("avatar"));
		
		Gson gson = new Gson();
		JsonElement json = gson.toJsonTree(formData);
		Usuario u = gson.fromJson(json, Usuario.class);
		
		
		try {
			u.setAvatar(req.getFile("avatar").getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("No puede asignar la foto al usuario");
		}
		servicioUsuarios.registarUsuario(u);
		
		String rutaRealDelProyecto = req.getServletContext().getRealPath("");
		
		//GestorArchivos.guardarAvatarUser(u, rutaRealDelProyecto, foto);
		
		return "Usuario registrado, ya puedes identificarte";
	}

	@RequestMapping("identificarUsuario")
	public ResponseEntity<String> identificarUsuario(String email, String pass, HttpServletRequest req){
		Usuario u = servicioUsuarios.obtenerUserPorMailYpass(email, pass);
		String resp= "";
		if (u != null) {
			req.getSession().setAttribute("usuario_identificado", u);
			resp = "ok,"+u.getNombre();
		} else
			resp = "Email o pass incorrecto";
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}
	
	@RequestMapping("logout")
	public ResponseEntity<String> logout(HttpServletRequest req){
		req.getSession().invalidate();
		return new ResponseEntity<String>("ok", HttpStatus.OK); 
	}
}
