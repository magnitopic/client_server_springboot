package tienda.discos.constantesSQL;

public class ConstantesSQL {

	public final static String SQL_OBTENER_DISCOS_JSON = 
			"SELECT d.id, d.ano, d.artista, d.discografica, d.nombre, d.precio, g.nombre AS nombre_genero "
			+ "FROM disco as d, genero as g "
			+ "WHERE d.genero_id = g.id and d.alta = 1 "
			+ "ORDER BY d.id DESC";

	public static final String SQL_OBTENER_GENEROS_PARA_DESPLEGABLE = 
			"select id, nombre from genero order by id asc";

	public static final String SQL_OBTENER_PRODUCTOS_CARRITO = 
			"SELECT d.id as disco_id, d.ano, d.artista, d.discografica, d.nombre, d.precio as precio_disco, pc.cantidad "
			+ "FROM disco as d, producto_carrito as pc "
			+ "WHERE pc.disco_id = d.id and pc.carrito_id = :par_variable "
			+ "order by pc.id asc";

	public final static String SQL_OBTENER_DETALLES_DISCO = 
			"SELECT d.id, d.ano, d.artista, d.discografica, d.nombre, d.precio, g.nombre AS genero "
			+ "FROM disco as d, genero as g "
			+ "WHERE d.genero_id = g.id and d.id = :id";

	public static final String SQL_BORRAR_PRODUCTOS_CARRITO = 
			"delete from producto_carrito where carrito_id = :carrito_id ";

	public static final String SQL_BORRAR_PRODUCTO_CARRITO = 
			"delete from producto_carrito where carrito_id = :carrito_id and disco_id= :id_disco";
}
