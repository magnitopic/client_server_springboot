package tienda.discos.utilidadesSetUp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tienda.discos.constantes.EstadosPedido;
import tienda.discos.model.Disco;
import tienda.discos.model.Genero;
import tienda.discos.model.Pedido;
import tienda.discos.model.ProductoPedido;
import tienda.discos.model.Usuario;
import tienda.discos.model.setup.SetUp;

@Service
@Transactional
public class ServicioSetUpImpl implements ServicioSetUp {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void prepararSetup() {
		SetUp setUp = null;
		try {
			setUp = (SetUp) entityManager.createQuery("select s from SetUp s").getSingleResult();			
		} catch (NoResultException e) {
			System.out.println("No se encontró un registro de SetUp, comenzamos setup...");
		}
		if (setUp == null || !setUp.isCompletado()) {
			// Copiar imagenes generales
			
			// crear generos
			Genero rock = new Genero("Rock");
			entityManager.persist(rock);
			Genero indie = new Genero("Indie");
			entityManager.persist(indie);
			Genero pop = new Genero("Pop");
			entityManager.persist(pop);
			Genero country = new Genero("Country");
			entityManager.persist(country);
			
			// Crear registros de discos
			Disco d1 = new Disco("Comedown Machine", "The Strokes", "RCA", "2013", 27.99);
			d1.setGenero(indie);
			d1.setImagenPortada(copiarImagenBase("http://localhost:8080/imagenes_base/1.jpg"));
			////copiarImagenBase(rutaReal, "/imagenes_base/1.jpg", "/subidas/1.jpg");
			Disco d2 = new Disco("The New Abnormal", "The Strokes", "RCA", "2020", 19.99);
			d2.setGenero(indie);
			d2.setImagenPortada(copiarImagenBase("http://localhost:8080/imagenes_base/2.jpg"));
			//copiarImagenBase(rutaReal, "/imagenes_base/2.jpg", "/subidas/2.jpg");
			Disco d3 = new Disco("Tranquility Base Hotel & Casino", "The Arctic Monkeys", "Domino", "2018", 21.99);
			d3.setGenero(rock);
			d3.setImagenPortada(copiarImagenBase("http://localhost:8080/imagenes_base/3.jpg"));
			//copiarImagenBase(rutaReal, "/imagenes_base/3.jpg", "/subidas/3.jpg");
			Disco d4 = new Disco("Blue Album", "Weezer", "DGC", "1994", 21.99);
			d4.setGenero(indie);
			d4.setImagenPortada(copiarImagenBase("http://localhost:8080/imagenes_base/4.jpg"));
			//copiarImagenBase(rutaReal, "/imagenes_base/4.jpg", "/subidas/4.jpg");
			Disco d5 = new Disco("Stadium Arcadium", "Red Hot Chili Peppers", "Warner", "2006", 21.99);
			d5.setGenero(rock);
			d5.setImagenPortada(copiarImagenBase("http://localhost:8080/imagenes_base/5.jpg"));
			//copiarImagenBase(rutaReal, "/imagenes_base/5.jpg", "/subidas/5.jpg");
			entityManager.persist(d1);
			entityManager.persist(d2);
			entityManager.persist(d3);
			entityManager.persist(d4);
			entityManager.persist(d5);
			// Crear registros de usuarios
			Usuario u1 = new Usuario("Magnitopic", "testMail@mail.com", "1234Password");
			//copiarImagenBase(rutaReal, "/imagenes_base/u1.jpg", "/subidas/u1.jpg");
			u1.setAvatar(copiarImagenBase("http://localhost:8080/imagenes_base_usuarios/1.jpg"));
			Usuario u2 = new Usuario("user", "mailTest@mail.com", "123");
			//copiarImagenBase(rutaReal, "/imagenes_base/u2.jpg", "/subidas/u2.jpg");
			u2.setAvatar(copiarImagenBase("http://localhost:8080/imagenes_base_usuarios/2.jpg"));
			Usuario u3 = new Usuario("mag", "mail@mail.com", "123");
			//copiarImagenBase(rutaReal, "/imagenes_base/u3.jpg", "/subidas/u3.jpg");
			u3.setAvatar(copiarImagenBase("http://localhost:8080/imagenes_base_usuarios/3.jpg"));
			entityManager.persist(u1);
			entityManager.persist(u2);
			entityManager.persist(u3);
			// Crear unos pedidos por defecto
			
			Pedido p1 = new Pedido();
			p1.setNombreCompleto("N. Completo");
			p1.setDireccion("Info Dirección");
			p1.setProvincia("Info Provincia");
			p1.setTipoTarjeta("VISA");
			p1.setNumeroTarjeta("123 456 789");
			p1.setTitularTargeta("Info Titular");
			p1.setUsuario(u3);
			p1.setEstado(EstadosPedido.TERMINADO);
			entityManager.persist(p1);
			ProductoPedido pp1 = new ProductoPedido();
			pp1.setPedido(p1);
			pp1.setDisco(d1);
			pp1.setCantidad(2);
			entityManager.persist(pp1);

			Pedido p2 = new Pedido();
			p2.setNombreCompleto("N. Completo");
			p2.setDireccion("Info Dirección");
			p2.setProvincia("Info Provincia");
			p2.setTipoTarjeta("VISA");
			p2.setNumeroTarjeta("123 456 789");
			p2.setTitularTargeta("Info Titular");
			p2.setUsuario(u2);
			p2.setEstado(EstadosPedido.TERMINADO);
			entityManager.persist(p2);
			ProductoPedido pp2 = new ProductoPedido();
			pp2.setPedido(p2);
			pp2.setDisco(d3);
			pp2.setCantidad(2);
			entityManager.persist(pp2);
			
			
			// una vez realizado los registros iniciales marcaremos el setup como completado
			SetUp registroSetUp = new SetUp();
			registroSetUp.setCompletado(true);
			entityManager.persist(registroSetUp);
		}
	}
	
	private byte[] copiarImagenBase(String rutaOrigen) {
		byte[] info = null;
		try {
			URL url = new URL(rutaOrigen);
			info = IOUtils.toByteArray(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No se puedo copiar la imagen base.");
			e.printStackTrace();
		}
		return info;
	}
	
}