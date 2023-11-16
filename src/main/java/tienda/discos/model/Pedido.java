package tienda.discos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	@OneToMany(mappedBy = "pedido")
	private List<ProductoPedido> productoPedido = new ArrayList<ProductoPedido>();
	
	@ManyToOne(targetEntity = Usuario.class, optional = false)
	private Usuario usuario;
	
	@Id
	@GeneratedValue
	private int id;
	
	// se pide en paso 1:
	private String nombreCompleto;
	private String direccion;
	private String provincia;
	private String titularTarjeta;
	private String estado;
	
	// se pide en paso 2:
	private String titularTargeta;
	private String numeroTarjeta;
	private String tipoTarjeta;
	
	// se pide en paso 3:
	private String regalo;
	private String observaciones;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<ProductoPedido> getProductoPedido() {
		return productoPedido;
	}
	public void setProductoPedido(List<ProductoPedido> productoPedido) {
		this.productoPedido = productoPedido;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getTitularTarjeta() {
		return titularTarjeta;
	}
	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}
	public String getTitularTargeta() {
		return titularTargeta;
	}
	public void setTitularTargeta(String titularTargeta) {
		this.titularTargeta = titularTargeta;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public String getRegalo() {
		return regalo;
	}
	public void setRegalo(String regalo) {
		this.regalo = regalo;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	@Override
	public String toString() {
		return "Pedido [productoPedido=" + productoPedido + ", usuario=" + usuario + ", id=" + id + ", nombreCompleto="
				+ nombreCompleto + ", direccion=" + direccion + ", provincia=" + provincia + ", titularTarjeta="
				+ titularTarjeta + ", estado=" + estado + ", titularTargeta=" + titularTargeta + ", numeroTarjeta="
				+ numeroTarjeta + ", tipoTarjeta=" + tipoTarjeta + "]";
	}
}