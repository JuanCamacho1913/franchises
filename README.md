![Descripción alternativa](src/main/resources/static/franchise.jpg)

# Documentación del Proyecto Franchise 

## Descripción
**La aplicación facilita la gestión descentralizada de negocios con múltiples ubicaciones, permitiendo un control detallado de cada franquicia, sus sucursales y el inventario de productos en cada punto de venta. Esto es útil para empresas con estructura de franquiciamiento que necesitan un sistema centralizado pero flexible para administrar sus operaciones.**

## Características
Desarrollé una aplicación web utilizando **Java con el framework Spring Boot y WebFlux**. implementando programación reactiva para mejorar el rendimiento y la escalabilidad. Las características principales incluyen:
   - Uso de flujos reactivos con Reactor.
   - Endpoints no bloqueantes.
   - Manejo asíncrono de solicitudes.
   - Base de datos no relacionales.
1. **La implementación reactiva permite procesar múltiples solicitudes concurrentemente sin bloquear hilos, optimizando el rendimiento de la aplicación en escenarios de alta carga. Tecnologías clave:**
    - Spring WebFlux.
    - Project Reactor.
    - Programación funcional.

2. **Almacenamiento en Base de Datos**: Se implementa un esquema de base de datos, preferiblemente MongoDB, para almacenar toda la información necesaria. Esto incluye datos con franquicia, sucursal y producto.

## Configuración
Se debe de configurar en el archivo application.properties las propiedades correspondientes para MongoDB:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/dbFranchiseProject
```
## Paquetes del Proyecto
El proyecto está organizado en los siguientes paquetes:

- `com.example.franchise.controller`: Define el manejo de rutas y endpoints, recibe y valida datos de entrada, serializa datos de salida. gestionando los diferentes tipos de metodos HTTP y procesa la lógica de negocio.
- `com.example.franchise.domain`: Contiene clases para capturar errores de la aplicación y devolverlos como respuesta. Define la logica de negocio de la aplicación y es un puente para conectarse con la capa de persistencia.
- `com.example.franchise.persistence`: Define documentos a la base de datos y conexion con la base de datos.
- `com.example.franchise.presentation`: Contiene clases DTO para toda la aplicacion.
- `com.example.franchise.utils`: Contiene mapeadores para convertir entre documentos y DTO.

## Clases Principales
A continuación, se describen algunas de las clases principales del proyecto:

### `IFranchiseService`, `IBranchService`, `IProductService`
- Interfaces de servicio que definen operaciones con franquicia, sucursal y producto. Sus implementaciones se encuentran en `FranchiseServiceImpl`,`BranchServiceImpl`,`ProductServiceImpl`, respectivamente.
### Controladores
- La aplicación implementa una estructura modular de rutas `AppController` y handlers reactivos `FranchiseHandler, BranchHandler, ProductHandler`, separando las responsabilidades de cada componente para lograr una gestión eficiente y escalable de las diferentes entidades del sistema.

### Rutas:

**URL:**
```properties
http://localhost:8080
```
**Franchise:**

- **POST**
- `/v1/franchise/create`: Agrega una nueva franquicia.
- `/v1/franchise/branch/add/{idFranchise}`: Agrega una nueva sucursal a una franquicia.
- **PUT**
- `/v1/franchise/name/update`: Actualiza el nombre de una franquicia.

**Braches:**

- **POST**
- `/v1/branch/product/add/{idBranch}`: Agrega un nuevo producto a una sucursal.
- `/v1/branch/product/remove`: Elimina un nuevo producto a una sucursal.
- **PUT**
- `/v1/branch/name/update`: Actualiza el nombre de una sucursal.

**Product:**

- **POST**
- `/v1/product/stock`: Modificar el stock de un producto.
- `/v1/product/stock/max`: Muestra cual es el producto que más stock tiene por sucursal para una franquicia puntual.
- **PUT**
- `/v1/product/name/update`: Actualiza el nombre de un producto.

## Manejo de Excepciones
El proyecto incluye un sistema de manejo de excepciones que garantiza una respuesta adecuada a diferentes tipos de errores, El manejo de excepciones se realiza a través de las clases `BadRequestException` y `ElementNotFoundException`.

## Pasos Para descargar este archivo
1. En GitHub.com, navega a la página principal del repositorio.
2. Encima de la lista de archivos, haz clic en botón verde **<> Code**.
3. Haz clic en **Download ZIP**.

## Pasos Para descargar un fichero
1. En GitHub.com, navega a la página principal del repositorio.
2. Navegas hasta el fichero y damos clic para ingresar al fichero.
2. En la parte superior del fichero, haz clic en **Raw**.
3. Te llevará al contenido del fichero, haz clic derecho y guardar como.

## Resumen
Este documento proporciona una visión general de la estructura del proyecto, sus componentes claves y configuraciones.
