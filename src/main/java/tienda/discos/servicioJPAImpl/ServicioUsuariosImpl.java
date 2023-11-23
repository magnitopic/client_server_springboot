package tienda.discos.servicioJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.ImplicitNameSource;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tienda.discos.constantesSQL.ConstantesSQL;
import tienda.discos.model.Usuario;
import tienda.discos.servicios.ServicioUsuarios;

@Service
@Transactional
public class ServicioUsuariosImpl implements ServicioUsuarios {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void registarUsuario(Usuario u) {
		entityManager.persist(u);
	}

	@Override
	public Usuario obtenerUserPorMailYpass(String email, String pass) {
		Query query = entityManager.createQuery("select u from Usuario u where u.email = :email and u.pass = :pass");
		query.setParameter("email", email);
		query.setParameter("pass", pass);

		List<Usuario> resultado = query.getResultList();
		if (resultado.size() == 0) {
			return null;
		} else
			return resultado.get(0);
	}

	@Override
	public Usuario obtenerUserPorId(int id) {
		return (Usuario) entityManager.createQuery("select u from Usuario u where u.id = :user_id")
				.setParameter("user_id", id)
				.getResultList().get(0);
	}
	
	public Usuario newObtenerUserPorId(int id) {
		Query query = entityManager.createQuery("select u from Usuario u where u.id = :user_id");
		query.setParameter("user_id", id);

		List<Usuario> resultado = query.getResultList();
		if (resultado.size() == 0) {
			return null;
		} else
			return resultado.get(0);
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		return entityManager.createQuery("select u from Usuario u")
				.getResultList();
	}
}
