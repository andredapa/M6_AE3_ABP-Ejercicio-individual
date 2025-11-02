-- Tabla específica requerida para el módulo JDBC Template
CREATE TABLE IF NOT EXISTS LIBROS_JDBC (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    anio_publicacion INT
);