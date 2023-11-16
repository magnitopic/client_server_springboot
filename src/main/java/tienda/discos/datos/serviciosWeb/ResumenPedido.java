package tienda.discos.datos.serviciosWeb;

import java.util.Map;

import antlr.collections.List;


// Esta es la info que recibirá el usuario para confirmar el pedidio
public class ResumenPedido {
	
	// Estos son los discos que hay en el carrito
	private java.util.List<Map<String, Object>> Discos;
	
	// datos del paso 1
	private String nombreCompleto;
	private String direccion;
	private String provincia;
	
	// datos del paso 2
	private String titularTarjeta;
	private String numeroTarjera;
	private String tipoTarjeta;

	// se pide en paso 3:
	private String regalo;
	private String observaciones;
	
	public java.util.List<Map<String, Object>> getDiscos() {
		return Discos;
	}
	public void setDiscos(java.util.List<Map<String, Object>> discos) {
		Discos = discos;
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
	public String getNumeroTarjera() {
		return numeroTarjera;
	}
	public void setNumeroTarjera(String numeroTarjera) {
		this.numeroTarjera = numeroTarjera;
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
	
	
}