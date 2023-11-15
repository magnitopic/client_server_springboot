//funciones de los pasos para el check out
function checkout_paso_0(){
	//mostrar al usuario un formulario donde insertar la informacion de envio
	$("#contenedor").html(plantillaCheckout_1);
	$("#aceptar_paso_1").click(checkout_paso_1_aceptar); 	
}//end checkout_paso_0

function checkout_paso_1_aceptar(){
	//recoger los valores introducidos y mandarlos al servidor
	var nombre = $("#campo_nombre").val();
	var direccion = $("#campo_direccion").val();
	var provincia = $("#campo_provincia").val();
	
	//ahora lo suyo seria validar los valores recogidos
	//...
	
	//mandar los valores al servicio web de pedidos
	$.post("servicioWebPedidos/paso1",
			{
				nombre: nombre,
				direccion: direccion,
				provincia: provincia
			}
	).done(function(res){
		if(res == "ok"){
			$("#contenedor").html(plantillaCheckout_2);
			$("#checkout2_aceptar").click(checkout_paso_2_aceptar);
		}else{
			alert(res);
		}
	});//end done	
	
}//end checkout_paso_1_aceptar

function checkout_paso_2_aceptar(){
	var tipo_tarjeta = $("#tipo_tarjeta").find(":selected").val();
	var numero_tarjeta = $("#numero_tarjeta").val();
	var titular_tarjeta = $("#titular_tarjeta").val();
	$.post("servicioWebPedidos/paso2",{
		tarjeta: tipo_tarjeta,
		numero: numero_tarjeta,
		titular: titular_tarjeta
	}).done(function(res){
		if(res == "ok"){
			$("#contenedor").html(plantillaCheckout_3);
			$("#checkout3_aceptar").click(checkout_paso_3_aceptar);
		}else{
			alert(res);
		}
	});//end done 
		
}//end checkout_paso_2_aceptar

function checkout_paso_3_aceptar(){
	var regalo = $("#regalo").is(":checked");
	var observaciones = $("#observaciones").val();
	$.post("servicioWebPedidos/paso3",{
		regalo: regalo,
		observaciones: observaciones
	}).done(function(res){
		var resumen_pedido = JSON.parse(res);
		var html = Mustache.render(plantillaCheckoutFinal, resumen_pedido);
		$("#contenedor").html(html);
		$("#boton_confirmar_pedido").click(function(){
			$.ajax("servicioWebPedidos/FinalPedido",{
				success: function(res){
					alert("repuesta del servicio web: " + res);
					mostrar_libros();
				}
			});
		});
	});
}
