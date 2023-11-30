package tienda.discos.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class Usuario {
	private String nombre;
	private String email;
	private String pass;
	private String tel;
	private String pais;

	@Lob
	@Column(name = "avatar")
	private byte[] avatar;

	@OneToOne
	private Carrito carrito;

	@Id
	@GeneratedValue
	private int id;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombre, String email, String pass, String tel, String pais) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
		this.tel = tel;
		this.pais = pais;
	}

	public Usuario(String nombre, String email, int id, String pass, String tel, String pais) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.id = id;
		this.pass = pass;
		this.tel = tel;
		this.pais = pais;
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

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", email=" + email + ", pass=" + pass + ", tel=" + tel + ", pais=" + pais
				+ ", avatar=" + Arrays.toString(avatar) + ", carrito=" + carrito + ", id=" + id + "]";
	}
}
