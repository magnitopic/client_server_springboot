package tienda.discos.servicioJPAImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;

import tienda.discos.constantesSQL.ConstantesSQL;
import tienda.discos.model.Disco;
import tienda.discos.model.Genero;
import tienda.discos.servicios.ServicioDiscos;

@Service
@Transactional
public class ServicioDiscosJPAImpl implements ServicioDiscos {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void registrarDisco(Disco d) {
		Genero g = entityManager.find(Genero.class, d.getIdGenero());
		d.setGenero(g);
		try {
			d.setImagenPortada(d.getFotoSubida().getBytes());
		} catch (IOException e) {
			System.out.println("No se puede procesar la foto subida.");
			e.printStackTrace();
		}
		d.setAlta(true);
		entityManager.persist(d);
	}

	@Override
	public List<Disco> obtenerDiscos() {
		return entityManager.createQuery("select d from Disco d where d.alta = true order by d.id desc")
				.getResultList();
	}

	@Override
	public void borrarDisco(int id) {
		// Ya no borramos el producto si no que lo damos de baja
		Disco d = entityManager.find(Disco.class, id);
		d.setAlta(false);
		entityManager.merge(d);
	}

	@Override
	public Disco obtenerDiscoPorId(int id) {
		return entityManager.find(Disco.class, id);
	}

	@Override
	public void gurdarCambioDisco(Disco d) {
		Genero g = entityManager.find(Genero.class, d.getIdGenero());
		d.setGenero(g);
		if (d.getFotoSubida().getSize() == 0) {
			System.out.println("No se subio una nueva foto, tenemos que mantener la anterior");
			Disco discoAnterior = entityManager.find(Disco.class, d.getId());
			d.setImagenPortada(discoAnterior.getImagenPortada());
		} else {
			System.out.println("Asignar la nueva foto al registro");
			try {
				d.setImagenPortada(d.getFotoSubida().getBytes());
			} catch (IOException e) {
				System.out.println("No se puede procesar la foto subida");
				e.printStackTrace();
			}
		}
		entityManager.merge(d);
	}

	@Override
	public List<Map<String, Object>> obtenerDiscosParaFormatJSON(String nombre, int comienzo, String artista,
			int maxPrecio) {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_DISCOS_JSON);
		NativeQueryImpl nativequery = (NativeQueryImpl) query;
		nativequery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		nativequery.setParameter("nombre", "%" + nombre + "%");
		nativequery.setParameter("comienzo", comienzo);
		nativequery.setParameter("maxPrecio", maxPrecio);
		nativequery.setParameter("artista", "%" + artista + "%");
		System.out.println("nombre: " + nombre);
		System.out.println("compiezo: " + comienzo);
		System.out.println("artista: " + artista);
		System.out.println("maxPrecio: " + maxPrecio);
		return nativequery.getResultList();
	}

	@Override
	public Map<String, Object> obtenerDetallesDisco(int idDisco) {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_DETALLES_DISCO);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setParameter("id", idDisco);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return (Map<String, Object>) nativeQuery.getResultList().get(0);
	}

	@Override
	public List<Disco> obtenerDiscosPorNombre(String nombre) {
		return entityManager.createQuery(
				"select d from Disco d where d.alta = true and lower(d.nombre) like lower(:nombre) order by d.id desc")
				.setParameter("nombre", "%" + nombre + "%")
				.getResultList();

	}

	@Override
	public List<Disco> obtenerDiscosPorNombreComienzoFin(String nombre, int comienzo, int resultadosPorPagina) {
		return entityManager.createQuery(
				"select d from Disco d where d.alta = true and lower(d.nombre) like lower(:nombre) order by d.id desc")
				.setParameter("nombre", "%" + nombre + "%")
				.setFirstResult(comienzo)
				.setMaxResults(resultadosPorPagina)
				.getResultList();
	}

	@Override
	public int obtenerTotalDiscos(String nombre, String artista, int maxPrecio) {
		Query q = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_TOTAL_DISCOS);
		q.setParameter("nombre", "%" + nombre + "%");
		q.setParameter("artista", "%" + artista + "%");
		q.setParameter("maxPrecio", maxPrecio);
		int totalDiscos = Integer.parseInt(q.getSingleResult().toString());
		return totalDiscos;
	}

	@Override
	public int obtenerTotalDiscos(String nombre) {
		Query q = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_TOTAL_DISCOS_ADMIN);
		q.setParameter("nombre", "%" + nombre + "%");
		int totalDiscos = Integer.parseInt(q.getSingleResult().toString());
		return totalDiscos;
	}

	@Override
	public List<String> obtenerArtistasDiscos() {
		return entityManager
				.createQuery("select distinct d.artista from Disco d where d.alta = true order by d.artista")
				.getResultList();
	}

	@Override
	public List<Map<String, Object>> obtener(String nombre, int comienzo, int resultadosPorPagina) {
		// TODO Auto-generated method stub
		return null;
	}

}
