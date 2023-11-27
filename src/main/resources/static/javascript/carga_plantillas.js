$.get("plantillas_mustache/inicio.html", (data) => {
	plantillaInicio = data;
});

$.get("plantillas_mustache/discos.html", (data) => {
	plantillaDiscos = data;
	setTimeout(mostrar_inicio(), 500);
});

$.get("plantillas_mustache/registro.html", (data) => {
	plantillaRegistro = data;
});

$.get("plantillas_mustache/identificar_usuario.html", (data) => {
	plantillaLogIn = data;
});

$.get("plantillas_mustache/carrito.html", (data) => {
	plantillaCarrito = data;
});

$.get("plantillas_mustache/detalles_disco.html", (data) => {
	plantillaDetallesDisco = data;
});

$.get("plantillas_mustache/check_out_1.html", (data) => {
	plantillaCheckoutUno = data;
});

$.get("plantillas_mustache/check_out_2.html", (data) => {
	plantillaCheckoutDos = data;
});

$.get("plantillas_mustache/check_out_3.html", (data) => {
	plantillaCheckoutTres = data;
});

$.get("plantillas_mustache/check_out_final.html", (data) => {
	plantillaCheckoutFinal = data;
});

$.get("plantillas_mustache/pedidos.html", (data) => {
	plantillaPedidos = data;
});

$.get("plantillas_mustache/perfil.html", (data) => {
	plantillaPerfil = data;
});