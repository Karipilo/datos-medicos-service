# Microservicio de Datos Médicos - Cuidado Seguro

## Descripción

El microservicio de Datos Médicos forma parte de la arquitectura de microservicios del sistema **Cuidado Seguro**.

Este servicio tiene como objetivo gestionar la información clínica complementaria de los pacientes, permitiendo almacenar antecedentes médicos, controles clínicos, observaciones y otros datos relevantes para el seguimiento médico.

El microservicio fue desarrollado utilizando Spring Boot y se integra con el resto de componentes del sistema mediante una arquitectura distribuida moderna.

---

# Tecnologías Utilizadas

## Lenguaje y Framework

* **Java 17**
* **Spring Boot 3**
* **Spring Web**
* **Spring Data JPA**
* **Spring Security**
* **Lombok**

## Base de Datos

* **MySQL Server**
* **Hibernate ORM**

## Seguridad

* **JWT (JSON Web Token)**
* **Spring Security**

## Documentación y Testing

* **Swagger OpenAPI**
* **Postman**

## DevOps y Arquitectura

* **Docker**
* **Arquitectura de Microservicios**
* **Arquitectura en Capas**

---

# Funcionalidad del Microservicio

El microservicio permite:

* Gestionar información médica de pacientes
* Registrar controles clínicos
* Administrar observaciones médicas
* Integrarse con autenticación JWT
* Proporcionar endpoints REST para consumo externo

---

# Arquitectura del Proyecto

El sistema implementa una arquitectura basada en capas.

## Capas implementadas

### Controller

Gestiona solicitudes HTTP y endpoints REST.

### Service

Contiene lógica de negocio del sistema.

### Repository

Gestiona acceso a datos mediante JPA.

### Model

Define entidades clínicas y médicas.

### Security

Gestiona validación JWT y protección de endpoints.

---

# Estructura del Proyecto

```bash
datos-medicos-service
│
├── controller
│
├── service
│
├── repository
│
├── model
│
├── security
│
├── config
│
└── DatosMedicosServiceApplication.java
```

---

# Dependencias Principales

| Dependencia     | Descripción                    |
| --------------- | ------------------------------ |
| Spring Web      | Creación API REST              |
| Spring Data JPA | Persistencia de datos          |
| Spring Security | Seguridad y autenticación      |
| JWT             | Validación de tokens           |
| MySQL Connector | Conexión base de datos         |
| Swagger OpenAPI | Documentación API              |
| Lombok          | Reducción de código repetitivo |

---

# Configuración del Proyecto

```properties
spring.application.name=datos-medicos-service

server.port=8083

# Configuración MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/datos_medicos
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
```

---

# Explicación de Configuración

| Configuración                 | Descripción                     |
| ----------------------------- | ------------------------------- |
| spring.application.name       | Nombre del microservicio        |
| server.port                   | Puerto de ejecución             |
| spring.datasource.url         | URL conexión MySQL              |
| spring.datasource.username    | Usuario base de datos           |
| spring.datasource.password    | Contraseña MySQL                |
| spring.jpa.hibernate.ddl-auto | Actualización automática tablas |
| spring.jpa.show-sql           | Mostrar SQL en consola          |

---

# Instalación del Proyecto

## Clonar repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
```

## Ingresar al proyecto

```bash
cd datos-medicos-service
```

## Compilar proyecto

```bash
mvn clean install
```

---

# Ejecución del Proyecto

## Ejecutar localmente

```bash
mvn spring-boot:run
```

---

# Configuración Docker

```yaml
services:
  datos-medicos-service:
    build: .
    container_name: datos-medicos-service
    ports:
      - "8083:8083"
```

---

# Seguridad JWT

El sistema implementa autenticación basada en JWT.

## Funcionalidades implementadas

* Protección de endpoints
* Validación de tokens
* Seguridad de solicitudes HTTP
* Integración con API Gateway

---

# Endpoints REST

| Método | Endpoint            | Descripción                   |
| ------ | ------------------- | ----------------------------- |
| GET    | /datos-medicos      | Obtener registros médicos     |
| GET    | /datos-medicos/{id} | Obtener registro por ID       |
| POST   | /datos-medicos      | Crear registro médico         |
| PUT    | /datos-medicos/{id} | Actualizar información médica |
| DELETE | /datos-medicos/{id} | Eliminar registro             |

---

# Swagger - Documentación API

## Acceso Swagger UI

```bash
http://localhost:8083/swagger-ui.html
```

## API Docs

```bash
http://localhost:8083/api-docs
```

---

# Integración con Otros Servicios

El microservicio se comunica con:

* API Gateway
* Backend For Frontend (BFF)
* Microservicio de Pacientes
* Microservicio de Autenticación

---

# Arquitectura Implementada

## Arquitectura en Capas

Permite separar responsabilidades del sistema.

## Microservicios

Desacopla funcionalidades clínicas independientes.

## Seguridad JWT

Protege información médica sensible.

## API REST

Permite integración con frontend y otros servicios.

---

# Requisitos Previos

Antes de ejecutar el proyecto se requiere:

* Java 17
* Maven
* MySQL Server
* Docker Desktop (opcional)
* Puerto 8083 disponible

---

# Puertos Utilizados

| Puerto | Descripción                 |
| ------ | --------------------------- |
| 8083   | Microservicio Datos Médicos |
| 8080   | API Gateway                 |

---

# Testing y Validación

Las pruebas pueden realizarse mediante:

* Swagger UI
* Postman
* Integración frontend

---

# Autor

Proyecto desarrollado para la asignatura de Fullstack III.

Desarrollado por: Karina Pimentel.

---

# Conclusión

El microservicio de Datos Médicos implementa una solución backend moderna basada en Spring Boot y arquitectura de microservicios.

El sistema permite:

* Gestionar información clínica
* Integrarse con autenticación JWT
* Exponer endpoints REST
* Mantener arquitectura desacoplada
* Escalar fun
