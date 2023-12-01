package tienda.discos.servicioJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.discos.constantes.EstadosPedido;
import tienda.discos.constantesSQL.ConstantesSQL;
import tienda.discos.datos.serviciosWeb.ResumenPedido;
import tienda.discos.model.Carrito;
import tienda.discos.model.Pedido;
import tienda.discos.model.ProductoCarrito;
import tienda.discos.model.ProductoPedido;
import tienda.discos.model.Usuario;
import tienda.discos.servicios.ServicioCarrito;
import tienda.discos.servicios.ServicioPedidos;

@Service
@Transactional
public class ServicioPedidosImpl implements ServicioPedidos {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ServicioCarrito servicioCarrito;

	@Override
	public List<Pedido> obtenerPedidos() {
		return entityManager.createQuery("select p from Pedido p order by p.id desc").getResultList();
	}

	@Override
	public Pedido obtenerPedidoPorId(int idPedido) {
		return (Pedido) entityManager.find(Pedido.class, idPedido);
	}

	@Override
	public void actualizarEstadoPedido(int idPedido, String estado) {
		Pedido pedido = entityManager.find(Pedido.class, idPedido);
		pedido.setEstado(estado);
		entityManager.merge(pedido);
	}

	@Override
	public void procesarPaso1(String nombre, String direccion, String provincia, int idUsuario) {
		// cada user solo podrá tener un pedido en estado "en proceso"
		// si el usuario finaliza un pedido "en proceso" el estado de dicho pedido
		// pasará a ser "terminado"
		// puede haber tantos pedidios "terminado" como sea necesario
		Pedido p = obtenerPedidoActual(idUsuario);
		p.setNombreCompleto(nombre);
		p.setDireccion(direccion);
		p.setProvincia(provincia);
		p.setEstado(EstadosPedido.EN_PROCESO);
		entityManager.merge(p);
	}

	@Override
	public void procesarPaso2(String titular, String numero, String tipoTarjeta, int idUsuario) {
		Pedido p = obtenerPedidoActual(idUsuario);
		p.setTitularTargeta(titular);
		p.setNumeroTarjeta(numero);
		p.setTipoTarjeta(tipoTarjeta);
		entityManager.merge(p);
	}

	@Override
	public void procesarPaso3(String regalo, String observaciones, int idUsuario) {
		Pedido p = obtenerPedidoActual(idUsuario);
		p.setRegalo(regalo);
		p.setObservaciones(observaciones);
		entityManager.merge(p);
	}

	@Override
	public ResumenPedido obtenerResumenDelPedido(int idUsuario) {
		ResumenPedido resumen = new ResumenPedido();
		Pedido p = obtenerPedidoActual(idUsuario);
		resumen.setNombreCompleto(p.getNombreCompleto());
		resumen.setDireccion(p.getDireccion());
		resumen.setProvincia(p.getProvincia());
		resumen.setTipoTarjeta(p.getTipoTarjeta());
		resumen.setTitularTarjeta(p.getTitularTargeta());
		resumen.setNumeroTarjera(p.getNumeroTarjeta());
		resumen.setRegalo(p.getRegalo());
		resumen.setObservaciones(p.getObservaciones());

		resumen.setDiscos(servicioCarrito.obtenerProductosCarrito(idUsuario));

		return resumen;
	}

	@Override
	public void confirmarPedido(int idUsuario) {
		Pedido p = obtenerPedidoActual(idUsuario);
		Usuario uBaseDatos = (Usuario) entityManager.find(
				Usuario.class, idUsuario);
		Carrito c = uBaseDatos.getCarrito();
		if (c != null && c.getProductosCarrito().size() > 0) {
			for (ProductoCarrito pc : c.getProductosCarrito()) {
				ProductoPedido pp = new ProductoPedido();
				pp.setCantidad(pc.getCantidad());
				pp.setDisco(pc.getDisco());
				p.getProductoPedido().add(pp);
				pp.setPedido(p);
				entityManager.persist(pp);
			}
		}
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_BORRAR_PRODUCTOS_CARRITO);
		query.setParameter("carrito_id", c.getId());
		query.executeUpdate();
		p.setEstado(EstadosPedido.TERMINADO);
		entityManager.merge(p);
	}

	private Pedido obtenerPedidoActual(int idUsuario) {
		Usuario uBaseDatos = (Usuario) entityManager.find(Usuario.class, idUsuario);
		Object pedidoEnProceso = null;
		List<Pedido> resultadoConsulta = entityManager.createQuery(
				"select p from Pedido p where p.estado = :estado and p.usuario.id = :usuario_id")
				.setParameter("estado", EstadosPedido.EN_PROCESO)
				.setParameter("usuario_id", uBaseDatos.getId())
				.getResultList();
		if (resultadoConsulta.size() == 1)
			pedidoEnProceso = resultadoConsulta.get(0);
		else
			pedidoEnProceso = null;

		Pedido p = null;
		if (pedidoEnProceso != null) {
			p = (Pedido) pedidoEnProceso;
		} else {
			p = new Pedido();
			p.setUsuario(uBaseDatos);
		}
		return p;
	}

	@Override
	public List<Pedido> obtenerPedidosDeCliente(int idUsuario) {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_PEDIDOS_POR_ID_USUARIO);
		NativeQueryImpl nativequery = (NativeQueryImpl) query;
		nativequery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		nativequery.setParameter("id_usuario", idUsuario);
		return nativequery.getResultList();
	}

}
