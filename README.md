📌 Comidita Martes – API REST

API REST desarrollada en Java 21 con Spring Boot, diseñada para gestionar comidas y participantes. Permite registrar comidas, asignar participantes, eliminarlos, y administrar toda la información de manera simple y ordenada.

🚀 Tecnologías utilizadas

Java 21

Spring Boot

Spring Web

Spring Data JPA

Hibernate

MySQL / PostgreSQL (según tu configuración)

Maven

📂 Funcionalidades principales
▶ Gestión de Participantes

Crear participantes

Listar todos los participantes

Buscar participante por ID

Actualizar participante

Eliminar participante

▶ Gestión de Comidas

Crear comidas

Listar comidas

Buscar comida por ID

Actualizar comida

Eliminar comida

➕ Agregar participantes a una comida

➖ Eliminar participantes de una comida

🛠 Estructura del proyecto
src/
 ├── controller/        # Endpoints REST
 ├── service/           # Lógica de negocio
 ├── repository/        # Acceso a base de datos
 ├── dto/               # Request / Response DTOs
 ├── mapper/            # Conversión entre entidades y DTOs
 └── model/             # Entidades JPA

▶ Ejecución del proyecto

Clonar el repositorio

git clone https://github.com/tu-usuario/tu-repo.git


Configurar tu application.properties o application.yml

Ejecutar con Maven

mvn spring-boot:run

📘 Documentación (Swagger)

Si activaste OpenAPI/Swagger:

URL general:

http://localhost:8080/swagger-ui.html

✔ Requisitos

Java 21

Maven 3+

Base de datos configurada (MySQL, PostgreSQL, etc.)

📄 Licencia

Proyecto de práctica personal – uso libre.
