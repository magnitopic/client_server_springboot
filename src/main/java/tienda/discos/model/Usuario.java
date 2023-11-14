package tienda.discos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Usuario {
	private String nombre;
	private String email;
	private String pass;
	
	@OneToOne
	private Carrito carrito;
	
	@Id
	@GeneratedValue
	private int id;
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	public Usuario(String nombre, String email, String pass) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
	}
	public Usuario(String nombre, String email, int id, String pass) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.id = id;
		this.pass = pass;
	}
	public Carrito getCarrito() {
		return carrito;
	}
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}