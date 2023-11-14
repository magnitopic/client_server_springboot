package tienda.discos.constantes;

public class EstadosPedido {
	//el usuario ha iniciado un proceso de formar pedido
	public static final String EN_PROCESO = "en proceso";
	
	//el usuario ha finalizado un pedido y un administrador podra gestionarlo
	public static final String TERMINADO = "terminado";
	
	//un administrador ha preparado "fisicamente" el envio para ser recogido
	//por la empresa de mensajeria
	public static final String LISTO_PARA_ENVIAR = "listo para enviar";
	
	//la empresa de mensajaria ha confirmado la recepcion del pedido
	public static final String RECIBIDO_POR_EL_CLIENTE = "recibido por el cliente";
}
