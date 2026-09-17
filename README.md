# 🛼 Escuela de Patinaje Roller Speed

Este proyecto está basado en un caso de estudio académico.

## 👥 Autores del Desarrollo

* Brayan Alejandro Durango Urrea
* Esteban Murcia Prieto
* Claudia Liliana Cabrera Cabezas
* Danilo Jose Pino Ospino
* Luis Javier García Orozco

Estudiantes de la Institución Universitaria Digital de Antioquia.

---

## 📖 Descripción del Proyecto

Aplicación backend desarrollada para la gestión de una escuela de patinaje. El proyecto permite administrar información relacionada con alumnos, clases, instructores y pagos mediante una API REST.

El proyecto fue desarrollado como parte de un caso de estudio académico, aplicando conceptos de desarrollo backend, programación orientada a objetos, persistencia de datos y creación de servicios REST.

## 🚀 Tecnologías utilizadas

* ☕ Java 11
* 🌱 Spring Boot 2.7.18
* 🌐 Spring Web
* 🗄️ Spring Data JPA
* 🐬 PostgreSQL
* 🧪 H2 Database (soporte adicional)
* 📦 Maven
* ✅ Bean Validation
* 📚 Springdoc OpenAPI 1.7.0 (Swagger UI)

## 📋 Funcionalidades

La API permite realizar operaciones CRUD sobre las siguientes entidades:

### 👨‍🎓 Alumnos

Permite:

* Registrar alumnos.
* Consultar todos los alumnos.
* Buscar un alumno por su ID.
* Buscar alumnos por nombre.
* Actualizar información de un alumno.
* Eliminar alumnos.
* Validar nombre, correo electrónico y edad.

Datos principales:

* id
* nombre
* email
* telefono
* edad
* nivel

### 🛼 Clases

Permite:

* Registrar clases.
* Consultar todas las clases.
* Buscar una clase por ID.
* Actualizar una clase.
* Eliminar una clase.

Datos principales:

* id
* nombre

### 👨‍🏫 Instructores

Permite:

* Registrar instructores.
* Consultar instructores.
* Buscar un instructor por ID.
* Actualizar instructores.
* Eliminar instructores.

Datos principales:

* id
* nombre

### 💰 Pagos

Permite:

* Registrar pagos.
* Consultar pagos.
* Buscar un pago por ID.
* Actualizar pagos.
* Eliminar pagos.

Datos principales:

* id
* monto

## 📁 Estructura del proyecto

```
Escuela de patinaje roller speed/
│
├── pom.xml
│
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── rollerspeed/
        │           ├── config/
        │           │   └── OpenApiConfig.java
        │           ├── Alumno.java
        │           ├── AlumnoController.java
        │           ├── AlumnoRepository.java
        │           ├── Clase.java
        │           ├── ClaseController.java
        │           ├── ClaseRepository.java
        │           ├── Instructor.java
        │           ├── InstructorController.java
        │           ├── InstructorRepository.java
        │           ├── Pago.java
        │           ├── PagoController.java
        │           ├── PagoRepository.java
        │           └── RollerSpeedApplication.java
        │
        └── resources/
            └── application.properties
```


## 🔗 Endpoints de la API

La aplicación se ejecuta por defecto en:

```
http://localhost:8080
```

### Interfaz web

El proyecto incluye una interfaz web estática para la escuela de patinaje, accesible en:

```
http://localhost:8080/
```

La interfaz web incluye:
* Página de inicio con información de la escuela
* Secciones de Misión, Visión y Valores
* Catálogo de servicios (Iniciación, Formación deportiva, Entrenamiento competitivo)
* Eventos de la escuela
* Formulario de inscripción
* Diseño responsivo y moderno


### Alumnos

| Método | Endpoint               | Descripción               |
| ------ | ---------------------- | ------------------------- |
| GET    | /alumnos             | Lista todos los alumnos   |
| GET    | /alumnos/{id}        | Consulta un alumno por ID |
| GET    | /alumnos?nombre=Juan | Busca alumnos por nombre  |
| POST   | /alumnos             | Registra un alumno        |
| PUT    | /alumnos/{id}        | Actualiza un alumno       |
| DELETE | /alumnos/{id}        | Elimina un alumno         |

### Clases

| Método | Endpoint       | Descripción               |
| ------ | -------------- | ------------------------- |
| GET    | /clases      | Lista todas las clases    |
| GET    | /clases/{id} | Consulta una clase por ID |
| POST   | /clases      | Registra una clase        |
| PUT    | /clases/{id} | Actualiza una clase       |
| DELETE | /clases/{id} | Elimina una clase         |

### Instructores

| Método | Endpoint             | Descripción                   |
| ------ | -------------------- | ----------------------------- |
| GET    | /instructores      | Lista todos los instructores  |
| GET    | /instructores/{id} | Consulta un instructor por ID |
| POST   | /instructores      | Registra un instructor        |
| PUT    | /instructores/{id} | Actualiza un instructor       |
| DELETE | /instructores/{id} | Elimina un instructor         |

### Pagos

| Método | Endpoint      | Descripción             |
| ------ | ------------- | ----------------------- |
| GET    | /pagos      | Lista todos los pagos   |
| GET    | /pagos/{id} | Consulta un pago por ID |
| POST   | /pagos      | Registra un pago        |
| PUT    | /pagos/{id} | Actualiza un pago       |
| DELETE | /pagos/{id} | Elimina un pago         |

## 📚 Documentación de la API (Swagger/OpenAPI)

El proyecto incluye **Springdoc OpenAPI** con **Swagger UI** para la documentación interactiva de la API REST.

### Swagger UI

La interfaz de Swagger UI está disponible en:

```
http://localhost:8080/swagger-ui.html
```

Esta interfaz permite:
* Visualizar todos los endpoints de la API
* Probar los endpoints directamente desde el navegador
* Ver los modelos de datos y esquemas
* Consultar documentación detallada de cada operación

### Documentación OpenAPI

El documento OpenAPI (JSON) está disponible en:

```
http://localhost:8080/v3/api-docs
```

### Configuración de SpringDoc

La configuración de Springdoc se encuentra en:

```
src/main/resources/application.properties
```

Propiedades configuradas:

```properties
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operations-sorter=alpha
springdoc.swagger-ui.tags-sorter=alpha
springdoc.swagger-ui.try-it-out-enabled=true
```

### Archivo de configuración OpenAPI

La configuración personalizada de OpenAPI se encuentra en:

```
src/main/java/com/rollerspeed/config/OpenApiConfig.java
```

Configuración actual:
* Título: "Roller Speed API"
* Descripción: "API REST para la gestión de la escuela de patinaje Roller Speed."
* Versión: "1.0.0"
* Contacto: https://github.com/teby032-jpg/Proyecto-de-node-js-escuela-de-patinaje-speed-rool

### Endpoints documentados

La API está completamente documentada con anotaciones de Swagger en cada controlador:

* **@Tag**: Organización por grupos (Alumnos, Clases, Instructores, Pagos)
* **@Operation**: Descripción de cada endpoint
* **@Parameter**: Documentación de parámetros
* **@ApiResponse**: Documentación de respuestas (códigos HTTP, descripciones, schemas)
* **@Schema**: Documentación de entidades (Alumno, Clase, Instructor, Pago)

## 🧪 Ejemplos de solicitudes

### Registrar un alumno

http
POST /alumnos
Content-Type: application/json


json
{
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "telefono": "3001234567",
  "edad": 15,
  "nivel": "Intermedio"
}


### Registrar una clase

http
POST /clases
Content-Type: application/json


json
{
  "nombre": "Patinaje Intermedio"
}


### Registrar un instructor

http
POST /instructores
Content-Type: application/json


json
{
  "nombre": "Carlos Gómez"
}


### Registrar un pago

http
POST /pagos
Content-Type: application/json


json
{
  "monto": 150000
}


## 🗄️ Base de datos

El proyecto utiliza **PostgreSQL** como base de datos principal para persistencia de datos.

La configuración se encuentra en:

text
src/main/resources/application.properties


Configuración utilizada:

properties
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/rollerspeed}
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:tu_password}
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL10Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


### Variables de entorno

Para configurar la conexión a PostgreSQL, puedes establecer las siguientes variables de entorno:

text
DB_URL=jdbc:postgresql://localhost:5432/rollerspeed
DB_USERNAME=postgres
DB_PASSWORD=tu_password


### Base de datos H2 (soporte adicional)

El proyecto también incluye H2 Database como base de datos en memoria para pruebas o desarrollo local.

Configuración H2:

properties
spring.datasource.url=jdbc:h2:mem:rollerspeed;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console


### Consola H2

Una vez iniciada la aplicación, la consola H2 puede consultarse en:

text
http://localhost:8080/h2-console


Configuración de conexión H2:

text
JDBC URL: jdbc:h2:mem:rollerspeed
User Name: sa
Password:


## ⚙️ Requisitos

Para ejecutar el proyecto se necesita:

* Java JDK 11 o superior.
* Maven.
* IDE compatible con proyectos Java, como IntelliJ IDEA, Eclipse o Visual Studio Code.

## ▶️ Instalación y ejecución

### 1. Clonar el repositorio

bash
git clone URL_DEL_REPOSITORIO


### 2. Entrar a la carpeta del proyecto

bash
cd "Escuela de patinaje roller speed"


### 3. Compilar el proyecto

bash
mvn clean install


### 4. Ejecutar la aplicación

bash
mvn spring-boot:run


La aplicación estará disponible en:

text
http://localhost:8080


## 🏗️ Arquitectura

El proyecto utiliza una estructura basada en capas:

text
Entidad
   ↓
Repository
   ↓
Controller
   ↓
API REST


### Entidades

Representan los datos que se almacenan en la base de datos:

* Alumno
* Clase
* Instructor
* Pago

### Repositories

Utilizan Spring Data JPA para realizar las operaciones de persistencia:

* AlumnoRepository
* ClaseRepository
* InstructorRepository
* PagoRepository

### Controllers

Exponen los endpoints de la API REST:

* AlumnoController
* ClaseController
* InstructorController
* PagoController

## ✅ Validaciones

El proyecto utiliza Bean Validation para validar los datos recibidos por la API.

En el caso de los alumnos:

* El nombre es obligatorio.
* El correo debe tener un formato válido.
* La edad mínima permitida es de 3 años.

## 📌 Estado del proyecto

🚧 Proyecto académico en desarrollo.

El proyecto contiene la implementación completa de una API REST para la administración de una escuela de patinaje con las siguientes características implementadas:

✅ API REST con operaciones CRUD para Alumnos, Clases, Instructores y Pagos
✅ Conexión a base de datos PostgreSQL para persistencia de datos
✅ Validación de datos con Bean Validation
✅ Documentación completa de la API con Springdoc OpenAPI y Swagger UI
✅ Interfaz web estática para la escuela de patinaje
✅ Soporte adicional para base de datos H2 para pruebas

El proyecto puede ampliarse posteriormente con nuevas funcionalidades como:
* Relaciones entre entidades
* Autenticación y autorización
* Sistema de login para la interfaz web
* Integración con pasarelas de pagos
* Reportes y estadísticas


Proyecto académico — Escuela de Patinaje Roller Speed.