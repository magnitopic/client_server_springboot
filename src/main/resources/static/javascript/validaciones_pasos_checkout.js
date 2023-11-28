let validatePasoUno = (nombre, direccion) => {
	let nombreRegex = /^[a-zA-Z ]{2,30}$/;
	let direccionRegex = /^[a-zA-Z0-9\s,'-]{1,50}$/;

	if (!nombreRegex.test(nombre))
		$("#errorNombre").html("El nombre no es valido");
	else $("#errorNombre").html("");
	if (!direccionRegex.test(direccion))
		$("#errorDireccion").html("La dirección no es valida");
	else $("#errorDireccion").html("");

	return !nombreRegex.test(nombre) || !direccionRegex.test(direccion);
};

let validatePasoDos = (tipo_tarjeta, numero_tarjeta, titular_tarjeta) => {
	let numero_tarjetaRegex = /^[0-9]{16}$/;
	let titular_tarjetaRegex = /^[a-zA-Z ]{2,30}$/;

	if (tipo_tarjeta == "0")
		$("#errorTipoTarjeta").html("Selecciona un tipo de tarjeta");
	else $("#errorTipoTarjeta").html("");
	if (!numero_tarjetaRegex.test(numero_tarjeta))
		$("#errorNumeroTarjeta").html("El numero de tarjeta no es valido");
	else $("#errorNumeroTarjeta").html("");
	if (!titular_tarjetaRegex.test(titular_tarjeta))
		$("#errorTitularTarjeta").html("El titular de la tarjeta no es valido");
	else $("#errorTitularTarjeta").html("");

	return (
		tipo_tarjeta == "0" ||
		!numero_tarjetaRegex.test(numero_tarjeta) ||
		!titular_tarjetaRegex.test(titular_tarjeta)
	);
};
