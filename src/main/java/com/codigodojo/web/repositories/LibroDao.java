package com.codigodojo.web.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.codigodojo.web.models.Libro;

@Repository
public class LibroDao {
	
	private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LibroDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper para mapear resultados a objetos LibroJdbc
    private RowMapper<Libro> libroRowMapper() {
        return (rs, rowNum) -> {
            Libro libro = new Libro();
            libro.setId(rs.getLong("id"));
            libro.setTitulo(rs.getString("titulo"));
            libro.setAnioPublicacion(rs.getInt("anio_publicacion"));
            return libro;
        };
    }

    // Método para insertar un nuevo libro
    public void insertar(Libro libro) {
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
             throw new IllegalArgumentException("El título del libro no puede estar vacío."); // Validación básica
        }
        String sql = "INSERT INTO LIBROS_JDBC (titulo, anio_publicacion) VALUES (?, ?)";
        jdbcTemplate.update(sql, libro.getTitulo(), libro.getAnioPublicacion());
    }

    // Método para consultar todos los libros
    public List<Libro> consultarTodos() {
        String sql = "SELECT id, titulo, anio_publicacion FROM LIBROS_JDBC";
        return jdbcTemplate.query(sql, libroRowMapper());
    }

    // Método para consultar por año
    public List<Libro> consultarPorAnio(int anio) {
        String sql = "SELECT id, titulo, anio_publicacion FROM LIBROS_JDBC WHERE anio_publicacion = ?";
        return jdbcTemplate.query(sql, libroRowMapper(), anio);
    }

    // Método para actualizar título
    public int actualizarTitulo(Long id, String nuevoTitulo) {
        String sql = "UPDATE LIBROS_JDBC SET titulo = ? WHERE id = ?";
        return jdbcTemplate.update(sql, nuevoTitulo, id);
    }

    // Método para eliminar un libro
    public int eliminar(Long id) {
        String sql = "DELETE FROM LIBROS_JDBC WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
