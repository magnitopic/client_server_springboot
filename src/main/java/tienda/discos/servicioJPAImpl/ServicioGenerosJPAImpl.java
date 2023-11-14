package tienda.discos.servicioJPAImpl;

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
}
