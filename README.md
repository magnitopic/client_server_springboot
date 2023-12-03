# Proyecto tienda online con Java usando Spring Boot 

## Tienda de discos de vinilo: __*Vinilo O's*__

Proyecto de tienda online de discos de vinilo, desarrollado con Java usando Spring Boot, Thymeleaf, Mustache, HTML, CSS, JavaScript, MySql y jQuery.

<div align="center">
<img width="80%" alt="Screenshot 2023-12-03 at 23 29 44" src="https://github.com/magnitopic/client_server_DAW/assets/21156058/0a1e9d21-f2dd-4e69-9021-f3d1818448f1">
</div>

## Descarga

El proyecto se puede descargar desde estos dos enlaces:

> Clonar repositorio de GitHub

* [GitHub](https://github.com/magnitopic/client_server_DAW)

> Descarga directa de un archivo comprimido

* [Descarga directa](https://www.dropbox.com/scl/fi/iw59yi9ftinb5a85yzylr/client_server_springboot.zip?rlkey=3v5n34o50lzzvjye4pjjn0w81&dl=1)

## Descripción

El proyecto consiste en una tienda online de discos de vinilo, en la que se pueden realizar las siguientes acciones:

Parte de usuario:
* Registro de usuarios.
* Inicio de sesión de usuarios.
* Cerrar sesión de usuarios.
* Añadir discos al carrito de la compra.
* Realizar pedidos con los discos en el carrito de la compra.
* Eliminar discos del carrito de la compra.
* Modificar la cantidad de discos en el carrito de la compra.
* Realizar pedidos.
* Ver el historial de pedidos.
* Ver el catálogo de discos.
* Ver los detalles de un disco.
* Búsqueda avanzada de discos.
	* Filtrar discos por input de búsqueda.
	* Filtrar discos por precio.
	* Filtrar discos por artista.

Parte de administrador:
* Acceder a la parte protegida de administración de la tienda.
* Cerrar sesión de administrador.
* Ver el listado de usuarios registrados.
* Modificar los datos de los usuarios registrados. 
* Ver el listado de pedidos realizados.
* Ver los detalles de los pedidos realizados.
* Modificar el estado de los pedidos realizados.
* Ver el listado de discos disponibles.
* Añadir discos al catálogo.
* Modificar los datos de los discos del catálogo.
* Dar de baja discos del catálogo.
* Ver los géneros de música disponibles.
* Añadir géneros al catálogo.
* Modificar los datos de los géneros del catálogo.
* Dar de baja géneros del catálogo.
* Cambiar el idioma de la parte de administración de la tienda.

## Entornos y ejecución

El proyecto se ha desarrollado usando *Eclipse IDE for Enterprise Java Developers*.
Se necesitarás una base de datos MySql corriendo en el puerto 3306. Yo usé *XAMPP* para ello.
Para ejecutar el proyecto, seguir los siguientes pasos:

1. Correr el proceso de MySql.
2. Descargar el proyecto.
3. Abrir Eclipse IDE.
4. Importar el proyecto como un `Existing Maven Project`.
5. Encontrar el fichero `DiscosApplication.java`, click derecho sobre él y seleccionar `Run As` -> `Java Application`.
6. Abrir un navegador web y acceder a la dirección `http://localhost:8080/`.

Si seguimos estos pasos correctamente, deberíamos ver la página de inicio de la tienda.