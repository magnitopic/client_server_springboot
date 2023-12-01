const provincias = ["Álava", "Albacete", "Alicante", "Almería", "Asturias", "Ávila", "Badajoz", "Barcelona", "Burgos", "Cáceres", "Cádiz", "Cantabria", "Castellón", "Ciudad Real", "Córdoba", "Cuenca", "Girona", "Granada", "Guadalajara", "Guipúzcoa", "Huelva", "Huesca", "Islas Baleares", "Jaén", "A Coruña", "La Rioja", "Las Palmas", "León", "Lleida", "Lugo", "Madrid", "Málaga", "Murcia", "Navarra", "Ourense", "Palencia", "Pontevedra", "Salamanca", "Segovia", "Sevilla", "Soria", "Tarragona", "Santa Cruz de Tenerife", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza"];

let checkout_paso_zero = () => {
	// mostrar al user un form donde mostrar la info del envÃ­o
	$("#contenedor").html(plantillaCheckoutUno);
	$("#aceptar_paso_1").click(check_out_uno);
};

let check_out_uno = () => {
	// recoger los valores introducidos y enviarlos al servidor
	let nombre = $("#campo_nombre").val();
	let direccion = $("#campo_direccion").val();
	let provincia = $("#campo_provincia").val();
	if (validatePasoUno(nombre, direccion))
		return;
	$.post("servicioWebPedidos/paso1", {
		nombre: nombre,
		direccion: direccion,
		provincia: provincia,
	}).done((res) => {
		if (res == "ok") {
			$("#contenedor").html(plantillaCheckoutDos);
			$("#aceptar_paso_2").click(checkout_paso_dos);
		} else {
			alert(res);
		}
	});
};

let checkout_paso_dos = () =>{
	let tipo_tarjeta = $("#tipo_tarjeta").find(":selected").val();
	let numero_tarjeta = $("#numero_tarjeta").val();
	let titular_tarjeta = $("#titular_tarjeta").val();
	console.log(numero_tarjeta);
	
	if (validatePasoDos(tipo_tarjeta, numero_tarjeta, titular_tarjeta))
		return;
	$.post("servicioWebPedidos/paso2",{
		tarjeta: tipo_tarjeta,
		numero: numero_tarjeta,
		titular: titular_tarjeta
	}).done((res) => {
		if (res == "ok"){
			$("#contenedor").html(plantillaCheckoutTres);
			$("#aceptar_paso_3").click(checkout_paso_tres);
		}else{
			alert(res);
		}
	})
}

let checkout_paso_tres = () => {
		let regalo = $("#regalo").is(":checked");
		let observaciones = $("#observaciones").val();
		$.post("servicioWebPedidos/paso3",{
			regalo:regalo,
			observaciones: observaciones
		}).done(function(res){
			console.log(res);
			let resumen_pedido = res;
			let html = Mustache.render(plantillaCheckoutFinal, resumen_pedido);
			$("#contenedor").html(html);
			$("#boton_confirmar_pedido").click(()=>{
				$.ajax("servicioWebPedidos/FinalPedido",{
					success: (res)=>{
						alert("repuesta del servicio web: " + res);
						mostrar_pedidos();
					}
				});
			});
		});
}