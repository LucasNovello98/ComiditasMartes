📌 ComiditasMartes – API REST

ComiditasMartes es una API REST desarrollada como proyecto personal para gestionar comidas y participantes dentro de un grupo de amigos.
Permite crear comidas, administrar participantes y asociarlos entre sí de forma sencilla.

El proyecto está desarrollado en Java 21 con Spring Boot, utiliza MySQL como base de datos y se encuentra dockerizado, pudiendo ser desplegado en un entorno Linux y administrado remotamente mediante SSH.

🚀 Tecnologías utilizadas

Java 21

Spring Boot

Spring Web

Spring Data JPA

Hibernate

MySQL

Docker & Docker Compose

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

📥 Carga inicial de datos

Carga automática de datos desde un archivo CSV al iniciar la aplicación (DataLoader)

🛠 Estructura del proyecto
src/
├── controller/     # Endpoints REST
├── service/        # Lógica de negocio
├── repository/     # Acceso a base de datos
├── dto/            # Request / Response DTOs
├── mapper/         # Conversión entre entidades y DTOs
├── model/          # Entidades JPA
└── config/         # Configuración y DataLoader

🐳 Ejecución con Docker (recomendado)
1️⃣ Clonar el repositorio
git clone https://github.com/LucasNovello98/ComiditasMartes.git
cd ComiditasMartes

2️⃣ Levantar la aplicación con Docker Compose
docker compose up -d --build


Esto levantará:

La API Spring Boot

La base de datos MySQL con persistencia de datos

La aplicación quedará disponible en:

http://localhost:8080

🖥 Deploy en Linux y administración remota

El proyecto puede ejecutarse en una máquina Linux como servidor, y administrarse remotamente desde otro equipo mediante SSH.

Ejemplo:

ssh usuario@IP_DEL_SERVIDOR
cd ComiditasMartes
docker compose up -d

▶ Ejecución sin Docker (modo local)
Requisitos

Java 21

Maven 3+

Base de datos configurada (MySQL)

Pasos
mvn spring-boot:run

📘 Documentación de la API (Swagger)

Si OpenAPI está habilitado:

http://localhost:8080/swagger-ui.html

✔ Requisitos

Java 21

Maven 3+

Docker y Docker Compose (opcional pero recomendado)