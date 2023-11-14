$("#inicio").click(() => {
	$("#contenedor").html(plantillaInicio);
});

$("#discos").click(() => {
	$.getJSON("servicioWebDiscos/obtenerDiscos", (res) => {
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
		$(".enlace_comprar_listado_principal").click(function(res){
			if ( nombre_login != "" ){
				var id_producto = $(this).attr("id-producto");
				alert("agregar producto de id: " + id_producto + " al carrito del usuario");
				$.post("servicioWebCarrito/agregarDisco",
						{
							id: id_producto,
							cantidad: 1
						}
				).done(function(res){
					alert(res);					
				});
			}else{
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
});

$("#registrarme").click(() => {
	$("#contenedor").html(plantillaRegistro);
	$("#form_registro_usuario").submit((e) => {
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
			}, //end success
		}); //end ajax
		e.preventDefault(); //evitamos envio de form, ya que todo en cliente
		//lo gestionamos con javascript
	}); //end submit
});

$("#logIn").click(() => {
	$("#contenedor").html(plantillaLogIn);
	if (typeof Cookies.get("email") !== "undefined")
		$("#email").val(Cookies.get("email"));
	if (typeof Cookies.get("pass") !== "undefined")
		$("#pass").val(Cookies.get("pass"));
	$("#form_login").submit((e) => {
		$.post("servicioWebUsuarios/identificarUsuario", {
			email: $("#email").val(),
			pass: $("#pass").val(),
		}).done((res) => {
			if (res.split(",")[0] == "ok") {
				console.log("Yes");
				nombre_login = res.split(",")[1];
				$("#mensaje_login").html(
					"¡Bienvenido de nuevo " +
						nombre_login.charAt(0).toUpperCase() +
						nombre_login.slice(1) +
						"!"
				);
				if ($("#recordar_datos").prop("checked")) {
					Cookies.set("email", $("#email").val(), {
						expires: 7,
					});
					Cookies.set("pass", $("#pass").val(), {
						expires: 7,
					});
				}
			} else {
				alert(res);
			}
		});
		e.preventDefault();
	});
});

$("#carrito").click(function () {
	if (nombre_login != "") {
		$.getJSON(
			"servicioWebCarrito/obtenerProductosCarrito",
			(res) => {
				if (res == null) {
					alert(
						"Aun no has agregado ningún producto al carrito"
					);
				} else {
					var html = Mustache.render(
						plantillaCarrito,
						res
					);
					$("#contenedor").html(html);
					$("#realizar_pedido").click(checkout_paso_zero);
				}
			}).fail(()=>{
				alert("Error: Prueba a identificarte");
			});
	} else {
		alert("debes identificarte para acceder al carrito");
	}
});

$("#logout").click(() => {
	$.ajax("servicioWebUsuarios/logout", {
		success: (res) => {
			if (res == "ok") {
				$("#contenedor").html(
					"hasta pronto " + nombre_login
				);
				$("#mensaje_login").html("No estás identificado");
				nombre_login = "";
			}
		},
	});
});
