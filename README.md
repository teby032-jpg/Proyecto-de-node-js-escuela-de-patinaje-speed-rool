# Proyecto-de-node-js-escuela-de-patinaje-speed-rool
este proyecto est basado en un caso de studio

# AUTORES DEL DESARROLLO ENCARGADOS DE ESTE PROYECTO

* Estudiante Brayan Alejandro Durango Urrea.
* Estudiante Esteban Murcia Prieto.
* Estudiante Claudia Liliana Cabrera Cabezas.
* Estudiante Danilo Jose Pino Ospino.
* Estudiante Luis Javier García Orozco.

Somos estudiantes con experiencia en la institucion universitaria digital de antioquia.

# 🛼 Escuela de Patinaje Roller Speed

Aplicación backend desarrollada para la gestión de una escuela de patinaje. El proyecto permite administrar información relacionada con alumnos, clases, instructores y pagos mediante una API REST.

El proyecto fue desarrollado como parte de un caso de estudio académico, aplicando conceptos de desarrollo backend, programación orientada a objetos, persistencia de datos y creación de servicios REST.

## 🚀 Tecnologías utilizadas

* ☕ Java 11
* 🌱 Spring Boot 2.7.18
* 🌐 Spring Web
* 🗄️ Spring Data JPA
* 🧪 H2 Database
* 🐬 MySQL Connector/J
* 📦 Maven
* ✅ Bean Validation

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

text
Escuela de patinaje roller speed/
│
├── pom.xml
│
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── rollerspeed/
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


## 🔗 Endpoints de la API

La aplicación se ejecuta por defecto en:

text
http://localhost:8080


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

Actualmente el proyecto utiliza H2 Database como base de datos en memoria.

La configuración se encuentra en:

text
src/main/resources/application.properties


Configuración utilizada:

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

Una vez iniciada la aplicación, la consola puede consultarse en:

text
http://localhost:8080/h2-console


Configuración de conexión:

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

El proyecto contiene la implementación de una API REST básica para la administración de una escuela de patinaje y puede ampliarse posteriormente con nuevas funcionalidades, relaciones entre entidades, autenticación, documentación mediante Swagger/OpenAPI y conexión a una base de datos persistente.


Proyecto académico — Escuela de Patinaje Roller Speed.