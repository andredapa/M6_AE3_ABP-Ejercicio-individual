package com.codigodojo.repositories;

import jakarta.persistence.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.codigodojo.web.models.Libro;

import jakarta.persistence.EntityManager;

@Repository 
public class LibroDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@PersistenceContext
    private EntityManager entityManager;

    public void guardar(Libro libro) {
        entityManager.persist(libro);
    }

    public Libro buscarPorId(Long id) {
        return entityManager.find(Libro.class, id);
    }

    public void eliminar(Libro libro) {
        entityManager.remove(entityManager.contains(libro) ? libro : entityManager.merge(libro));
    }

	


}
