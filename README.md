# FSIII_Tienda_Productos_Ar
FSIII - SUMATIVA - Arquetipo - Microservicio gestión de productos

# Ejecutar app Spring Boot
mvn spring-boot:run

# Levantar contenedor Docker
docker build -t tienda_productos_backend .
docker run --name tienda_productos_backend -p 8080:8080 tienda_productos_backend

# Patrones de diseño
- Builder, para la clase Producto. Ya que esto nos ayudara a mantener los atributos y mejorara la legibilidad y mantenibilidad.
- Singleton, para manejar el cache de los productos mas consultados.