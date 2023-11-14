package tienda.discos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="disco")
public class Disco {
	private String nombre;
	private String artista;
	private String discografica;
	private String ano;
	private Double precio;
	
	@Lob
	@Column(name ="imagen_portada")
	private byte[] imagenPortada;
	
	@OneToOne
	private ProductoCarrito productoCarrito;
	
	// vamos a indicar la associación entre disco y genero
	@ManyToOne
	private Genero genero;

	@Transient
	private int idGenero;
	
	@Transient
	private MultipartFile fotoSubida;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public Disco() {
		// TODO Auto-generated constructor stub
	}

	public Disco(String nombre, String artista, String discografica, String ano, Double precio) {
		super();
		this.nombre = nombre;
		this.artista = artista;
		this.discografica = discografica;
		this.ano = ano;
		this.precio = precio;
	}

	public Disco(String nombre, String artista, String discografica, String ano, Double precio, int id) {
		super();
		this.nombre = nombre;
		this.artista = artista;
		this.discografica = discografica;
		this.ano = ano;
		this.precio = precio;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getDiscografica() {
		return discografica;
	}

	public void setDiscografica(String discografica) {
		this.discografica = discografica;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public ProductoCarrito getProductoCarrito() {
		return productoCarrito;
	}

	public void setProductoCarrito(ProductoCarrito productoCarrito) {
		this.productoCarrito = productoCarrito;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public MultipartFile getFotoSubida() {
		return fotoSubida;
	}

	public void setFotoSubida(MultipartFile fotoSubida) {
		this.fotoSubida = fotoSubida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public byte[] getImagenPortada() {
		return imagenPortada;
	}

	public void setImagenPortada(byte[] imagenPortada) {
		this.imagenPortada = imagenPortada;
	}

	@Override
	public String toString() {
		return "Disco [nombre=" + nombre + ", artista=" + artista + ", discografica=" + discografica + ", ano=" + ano
				+ ", precio=" + precio + ", productoCarrito=" + productoCarrito + ", genero=" + genero + ", idGenero="
				+ idGenero + ", fotoSubida=" + fotoSubida + ", id=" + id + "]";
	}
	
}