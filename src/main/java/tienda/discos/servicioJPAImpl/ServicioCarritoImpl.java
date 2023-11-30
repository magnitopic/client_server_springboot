package tienda.discos.servicioJPAImpl;

import java.lang.annotation.Native;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tienda.discos.constantesSQL.ConstantesSQL;
import tienda.discos.model.Carrito;
import tienda.discos.model.Disco;
import tienda.discos.model.ProductoCarrito;
import tienda.discos.model.Usuario;
import tienda.discos.servicios.ServicioCarrito;


@Service
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void agreggarProducto(int idUsuario, int idProducto, int cantidad) {
		Usuario uBaseDatos = 
				(Usuario)entityManager.find(Usuario.class, idUsuario);
		
		Carrito c = uBaseDatos.getCarrito();
		if (c == null) {
			// si el usuario tiene un carrito asociado, lo creo y lo guardo en db
			c = new Carrito();
			c.setUsuario(uBaseDatos);
			uBaseDatos.setCarrito(c);
			entityManager.persist(c);
		}
		
		boolean producto_en_carrito = false;
		
		
		for (ProductoCarrito pc : c.getProductosCarrito()) {
			if (pc.getDisco().getId() == idProducto) {
				producto_en_carrito = true;
				pc.setCantidad(pc.getCantidad() + cantidad);
				entityManager.merge(pc);
			}
		}
		
		if (!producto_en_carrito) {
			ProductoCarrito pc = new ProductoCarrito();
			pc.setCarrito(c);
			pc.setDisco((Disco)entityManager.find(Disco.class, idProducto));
			pc.setCantidad(cantidad);
			entityManager.persist(pc);
		}		
		
	}

	@Override
	public void actualizarProductoCarrito(int idUsuario, int idProducto, int cantidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarProductoCarrito(int idUsuario, int idProducto) {
		Usuario u = entityManager.find(Usuario.class, idUsuario);
		Carrito c = u.getCarrito();
		if (c != null) {
			Query query = entityManager.createNativeQuery(
					ConstantesSQL.SQL_BORRAR_PRODUCTO_CARRITO);
			query.setParameter("carrito_id", c.getId());
			query.setParameter("id_disco", idProducto);
			query.executeUpdate();
		}
	}

	@Override
	public List<Map<String, Object>> obtenerProductosCarrito(int idUsuario) {
		Usuario u = (Usuario)entityManager.find(Usuario.class, idUsuario);
		Carrito c = u.getCarrito();
		List<Map<String, Object>> res = null;
		if(c != null ) {
			Query query = entityManager.createNativeQuery(
					ConstantesSQL.SQL_OBTENER_PRODUCTOS_CARRITO);
			query.setParameter("par_variable", c.getId());
			NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
			nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			res = nativeQuery.getResultList(); 
		}
		return res;
	}
}
