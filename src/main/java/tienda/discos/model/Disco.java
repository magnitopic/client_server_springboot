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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "disco")
public class Disco {
	@Size(min = 3, max = 40, message = "Nombre debe tener entre 3 y 40 caracteres")
	@NotEmpty(message = "Nombre no puede estar vacio")
	@Pattern(regexp = "[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ]+", message = "Titulo solo puede contener numero, letras y espacios")
	private String nombre;

	@NotEmpty(message = "Debes introducir un artista")
	@Size(min = 3, max = 40, message = "El artista debe tener entre 3 y 40 caracteres")
	private String artista;
	@NotEmpty(message = "La discográfica no puede estar vacía")
	private String discografica;
	@NotNull(message = "Debes intoducir un año")
	@Pattern(regexp = "\\d{4}$", message = "Se debe introducir una fecha valida")
	private String ano;
	@NotNull(message = "Debes insertar un precio")
	@Min(value = 1, message = "el precio mínimo es un euro")
	@Max(value = 250, message = "el precio maximo es 999€")
	private Double precio;

	private boolean alta = true;

	@Lob
	@Column(name = "imagen_portada")
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

	public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
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