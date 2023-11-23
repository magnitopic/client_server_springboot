var selectedElement = $("#inicio");

let mostrar_inicio = () => {
	selectedElement.removeClass("selected");
	selectedElement = $("#inicio");
	selectedElement.addClass("selected");
	$("#contenedor").html(plantillaInicio);
};

var nombre_a_buscar = "";

function mostrar_discos() {
	selectedElement.removeClass("selected");
	selectedElement = $("#discos");
	selectedElement.addClass("selected");
	$.getJSON("servicioWebDiscos/obtenerDiscos", { nombre: nombre_a_buscar }).done((res) => {
		//alert("respuesta del servidor: \n"+res);
		let texto_html = "";
		/*res.forEach((e)=>{
		e.fecha_hora_actual = new Date();
		e.precio = e.precio.toString().replace(".",",");
	})*/
		res.forEach((e) => {
			e.fecha_hora_actual = new Date();
			e.precio = e.precio.toString().replace(".", ",");
		});
		texto_html = Mustache.render(plantillaDiscos, res);
		$("#contenedor").html(texto_html);

		// indicar que hace el buscador

		$("#contenedor").html(texto_html);
		$("#nombre_buscador").val(nombre_a_buscar);
		$("#nombre_buscador").focus();
		$("#nombre_buscador").keyup(function(e) {
			nombre_a_buscar = $(this).val();
			mostrar_discos();
		});

		$(".enlace_comprar_listado_principal").click(function(res) {
			if (nombre_login != "") {
				var id_producto = $(this).attr("id-producto");
				alert("agregar producto de id: " + id_producto + " al carrito del usuario");
				$.post("servicioWebCarrito/agregarDisco",
					{
						id: id_producto,
						cantidad: 1
					}
				).done(function(res) {
					alert(res);
				});
			} else {
				alert("tienes que identificarte para poder comprar productos");
			}
		});
		$(".enlace_ver_detalles").click(() => {
			let id_disco = $(this).attr("id-producto");
			$.getJSON("servicioWebDiscos/obtenerDiscoDetalles", {
				id: id_producto,
			}).done((res) => {
				var html = Mustache.render(
					plantillaDetallesDisco,
					res
				);
				$("#contenedor").html(html);
			});
		});
	});
}

$("#discos").click(mostrar_discos);
$("#logo-container").click(mostrar_inicio);
$("#inicio").click(mostrar_inicio);

let logIn = () => {
	$("#contenedor").html(plantillaLogIn);
	if (typeof Cookies.get("email") !== "undefined")
		$("#email").val(Cookies.get("email"));
	if (typeof Cookies.get("pass") !== "undefined")
		$("#pass").val(Cookies.get("pass"));
	$("#registrarme").click(registarme);
	$("#form_login").submit((e) => {
		$.post("servicioWebUsuarios/identificarUsuario", {
			email: $("#email").val(),
			pass: $("#pass").val(),
		}).done((res) => {
			if (res.split(",")[0] == "ok") {
				nombre_login = res.split(",")[1];
				$("#user-msg-main").html(
					"Bienvenido"
				);
				$("#user-msg-main").addClass("user-msg-loged");
				$("#userPfp").attr("src", "mostrar_imagen_user?id=" + res.split(",")[2]);
				$("#user-msg").html(
					nombre_login.charAt(0).toUpperCase() +
					nombre_login.slice(1));
				if ($("#recordar_datos").prop("checked")) {
					Cookies.set("email", $("#email").val(), {
						expires: 7,
					});
					Cookies.set("pass", $("#pass").val(), {
						expires: 7,
					});
				}
				mostrar_discos();
			} else {
				alert(res);
			}
		});
		e.preventDefault();
	});
};
$("#logIn").click(logIn);


let registarme = () => {
	$("#contenedor").html(plantillaRegistro);
	$("#goToLogIn").click(logIn);
	$("#form_register").submit((e) => {
		let formulario = document.forms[0];
		let formData = new FormData(formulario);
		$.ajax("servicioWebUsuarios/registrarUsuario", {
			type: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: (res) => {
				alert(res);
				logIn();
			}, //end success
		}); //end ajax
		e.preventDefault(); //evitamos envio de form, ya que todo en cliente
		//lo gestionamos con javascript

	}); //end submit
};

$("#carrito").click(function() {
	if (nombre_login != "") {
		$.getJSON(
			"servicioWebCarrito/obtenerProductosCarrito",
			(res) => {
				if (res == null) {
					alert("Aun no has agregado ningún producto al carrito");
				} else {
					var html = Mustache.render(
						plantillaCarrito,
						res
					);
					$("#contenedor").html(html);
					selectedElement.removeClass("selected");
					selectedElement = $("#carrito");
					selectedElement.addClass("selected");
					$(".shop-can").click(function(e) {
						let id_disco = $(this).attr("id-disco");
						$.post("servicioWebCarrito/borrarProducto", {
							id: id_disco
						}).done((res) => {
							if (res == "ok") {
								$("#div-producto-" + id_disco).hide();
							} else {
								alert(res);
							}
						});
						e.preventDefault();
					});
					$("#realizar_pedido").click(checkout_paso_zero);
				}
			}).fail(() => { alert("El carrito está vacio"); });
	} else {
		alert("Debes iniciar sesión antes de ver tu carrito");
		selectedElement.removeClass("selected");
		selectedElement = $("#carrito");
		selectedElement.addClass("selected");
		logIn();
	}
});

$("#logout").click(() => {
	if (nombre_login != "") {
		$.ajax("servicioWebUsuarios/logout", {
			success: (res) => {
				if (res == "ok") {
					$("#user-msg").html("");
					$("#user-msg-main").html("Iniciar Sesión");
					$("#user-msg-main").removeClass("user-msg-loged");
					$("#userPfp").attr("src", "imgs/userIcon.png");
					nombre_login = "";
					mostrar_inicio();
				}
			},
		});
	}
});

let mostrar_pedidos = (e) => {
	selectedElement.removeClass("selected");
	selectedElement = $("#mispedidos");
	selectedElement.addClass("selected");
	let texto_html = "";
	if (nombre_login != "") {
		$.getJSON("servicioWebPedidos/obtenerPedidos", (res) => {
			console.log(res);
			texto_html = Mustache.render(plantillaPedidos, res);
			$("#contenedor").html(texto_html);
		}).fail(() => { alert("Error of some sort") });
	} else {
		alert("Debes iniciar sesión antes de ver tus pediddos");
		logIn();
	}
};
$("#mispedidos").click(mostrar_pedidos);

$("#misdatos").click((e) => {
	selectedElement.removeClass("selected");
	selectedElement = $("#misdatos");
	selectedElement.addClass("selected");
	if (nombre_login != "") {
		$.getJSON("servicioWebUsuarios/obtenerDatosUser", (res) => {
			console.log(res);
			texto_html = Mustache.render(plantillaPerfil, res);
			$("#contenedor").html(texto_html);
		}).fail(() => { alert("Error of some sort") });
	} else {
		alert("Debes iniciar sesión antes de ver tus datos");
		logIn();
	}
})
