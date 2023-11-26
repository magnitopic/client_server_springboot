package tienda.discos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Genero {
	// Una vez realizada la asociación en la calse disco, aquí debemos indicar la asociacion inversa
	// cascade indica como se puede propagar una operacion desde el dato actual
	// con cascade a tipo ALL estamos diciendo que una operacion aplicada a una genero
	// pueda ser porpagada a los libros asociados
	@OneToMany
	private List<Disco> disco = new ArrayList<Disco>();
		
	@Id
	@GeneratedValue
	private int id;
	
	@NotEmpty(message = "Nombre de genero no puede estar vacío")
	private String nombre;
	
	public Genero() {
		// TODO Auto-generated constructor stub
	}

	public Genero(String nombre) {
		super();
		this.nombre = nombre;
	}

	public List<Disco> getDisco() {
		return disco;
	}

	public void setDisco(List<Disco> disco) {
		this.disco = disco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}