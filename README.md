📌 Comidita Martes – API REST

Comidita Martes es un proyecto personal desarrollado para mí y mi grupo de amigos.
La API está construida en Java 21 con Spring Boot, y permite gestionar comidas y participantes, asignarlos, quitarlos y administrar toda la información de forma simple.

🚀 Tecnologías utilizadas

Java 21

Spring Boot

Spring Web

Spring Data JPA

Hibernate

MySQL / PostgreSQL (según configuración)

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

➕➖ Participantes en una comida

Agregar participantes a una comida

Eliminar participantes de una comida

🛠 Estructura del proyecto
src/
├── controller/     # Endpoints REST
├── service/        # Lógica de negocio
├── repository/     # Acceso a base de datos
├── dto/            # Request / Response DTOs
├── mapper/         # Conversión entre entidades y DTOs
└── model/          # Entidades JPA

▶ Ejecución del proyecto
1️⃣ Clonar el repositorio
git clone https://github.com/LucasNovello98/ComiditasMartes.git

2️⃣ Configurar tu application.properties o application.yml
3️⃣ Ejecutar con Maven
mvn spring-boot:run

📘 Documentación (Swagger)

Si activaste OpenAPI:

URL:

http://localhost:8080/swagger-ui.html

✔ Requisitos

Java 21

Maven 3+

Base de datos configurada (MySQL, PostgreSQL, etc.)

📄 Licencia

Proyecto personal y de práctica – uso libre.