package tienda.discos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Carrito {
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Usuario usuario;
	
	
	@OneToMany(mappedBy = "carrito")
	private List<ProductoCarrito> productosCarrito =
			new ArrayList<ProductoCarrito>();
	
	@Id
	@GeneratedValue
	private int id;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ProductoCarrito> getProductosCarrito() {
		return productosCarrito;
	}

	public void setProductosCarrito(List<ProductoCarrito> productosCarrito) {
		this.productosCarrito = productosCarrito;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}