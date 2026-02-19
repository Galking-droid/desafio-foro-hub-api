# Foro Hub API - Challenge Oracle Next Education

API REST desarrollada con Spring Boot para la gestión de tópicos de un foro, implementando validaciones de negocio, persistencia de datos y seguridad.

## 🛠️ Stack Tecnológico
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MySQL / Flyway** (Migraciones de base de datos)
- **Spring Security** (Autenticación/Autorización mediante JWT)
- **Maven** (Gestor de dependencias)

## 📋 Requisitos del Proyecto
Basado en los lineamientos del desafío Alura ONE:
1. **API REST**: Endpoints para crear, listar, actualizar y eliminar tópicos.
2. **Validaciones**: Uso de `@Valid` y Bean Validation para asegurar la integridad de los datos de entrada.
3. **Persistencia**: Mapeo objeto-relacional (ORM) para almacenamiento persistente.
4. **Seguridad**: Protección de endpoints sensibles.

## 🚀 Instalación y Configuración

### 1. Configuración de Base de Datos
Actualice el archivo 
`src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub_db
spring.datasource.username=USUARIO
spring.datasource.password=CONTRASEÑA
```

### 2. Ejecución
`./mvnw spring-boot:run`

### 📍 Endpoints Principales (Documentación)
| Método | Endpoint | Status Esperado | Acceso |
| :--- | :--- | :---: | :--- |
| `POST` | `/login` | 200 OK | Público |
| `POST` | `/topicos` | 201 Created | JWT Requerido |
| `GET` | `/topicos` | 200 OK | JWT Requerido |
| `DELETE`| `/topicos/{id}` | 204 No Content | JWT Requerido |

### Desarrollado por:
Joseph Gama.