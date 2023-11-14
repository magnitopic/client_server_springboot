package tienda.discos.utilidades;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import tienda.discos.model.Disco;
import tienda.discos.model.Usuario;

public class GestorArchivos {

	public static void guardarPortadaDisco(Disco d, String rutaReal) {
		
		MultipartFile archivo = d.getFotoSubida();
		String nombreArchivo = d.getId()+".jpg";
		
		//vamos a crear una carpeta de subidas (si no existe) en la ruta real del proyecto
		String rutaSubidas = rutaReal + "subidas";
		File fileRutaSubidas = new File(rutaSubidas);
		if( ! fileRutaSubidas.exists() ) {
			fileRutaSubidas.mkdirs();
		}
		//mover el archivo subido a dicha ruta poniendole el nombre indicado:
		if(archivo.getSize() > 0) {
			try {
				archivo.transferTo(new File(rutaSubidas,nombreArchivo));
				System.out.println("archivo movido a: " + rutaSubidas);
			} catch (IllegalStateException | IOException e) {
				System.out.println("no pude mover el archivo a la ruta de subidas");
				e.printStackTrace();
			}	
		}else {
			System.out.println(" se registro un producto sin imagen, no hay problema, "
					+ " de momento la imagen es opcional");
		}
	}//end guardarPortadaLibro
	
	public static void guardarAvatarUsuario(Usuario u, String rutaReal, 
			MultipartFile avatar) {
		String nombreArchivo = u.getId() + ".jpg";
		File fileCarpetaAvatares = new File(rutaReal+"subidas_usuarios");
		if ( ! fileCarpetaAvatares.exists() ) {
			fileCarpetaAvatares.mkdirs();
		}
		if( avatar.getSize() > 0) {
			try {
				avatar.transferTo(new File(rutaReal+"subidas_usuarios",nombreArchivo));
				System.out.println("avatar de usuario disponible en: " + rutaReal);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end catch			
		}//end if		
	}//end guardarAvatarUsuario
		
	
}//end class
