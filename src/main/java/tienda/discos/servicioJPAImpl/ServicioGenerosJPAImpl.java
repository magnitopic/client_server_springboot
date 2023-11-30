package tienda.discos.servicioJPAImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import tienda.discos.model.Genero;
import tienda.discos.servicios.ServicioGeneros;

@Service
@Transactional
public class ServicioGenerosJPAImpl implements ServicioGeneros {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Map<String, String> obtenerGenerosParaDesplegable() {
		return null;
	}

	@Override
	public List<Genero> obtenerGeneros() {
		return entityManager.createQuery("select g from Genero g").getResultList();
	}

	@Override
	public void registrarGenero(Genero g) {
		entityManager.persist(g);
	}

	@Override
	public Genero obtenerGeneroPorId(Integer id) {
		return entityManager.find(Genero.class, id);
	}

	@Override
	public void guardarCambiosGenero(Genero generoEditar) {
		entityManager.merge(generoEditar);
	}

	@Override
	public void eliminarGenero(Integer id) {
		Genero g = entityManager.find(Genero.class, id);
		entityManager.remove(g);
	}
}
