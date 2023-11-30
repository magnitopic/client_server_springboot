let regexp_nombre = /^[a-z áéíóú]{2,10}$/i

let regexp_email = /^([a-z0-9_\.-]+)@([0-9a-z\.-]+)\.([a-z\.]+)$/i;

let regexp_pass = /^[a-z0-9áéíóúñ]{3,10}$/i;

let validarNombre = (nombre)=>{
	if (regexp_nombre.test(nombre))
		return true;
	else{
		alert("Nombre invalido");
		return false;
	}
}

let validarEmail = (email)=>{
	if (regexp_email.test(email))
		return true;
	else{
		alert("Correo electronico invalido");
		return false;
	}
}

let validarPass = (pass)=>{
	if (regexp_pass.test(pass))
		return true;
	else{
		alert("Contraseña invalida");
		return false;
	}
}

let validarTel = (tel)=>{
	if (tel.length == 9)
		return true;
	else{
		alert("Telefono invalido");
		return false;
	}
}

let validarPais = (pais)=>{
	if (pais.length > 0)
		return true;
	else{
		alert("País invalido");
		return false;
	}
}