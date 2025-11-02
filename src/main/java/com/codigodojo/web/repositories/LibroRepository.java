package com.codigodojo.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codigodojo.web.models.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
	List<Libro> findByTitulo(String titulo);

}
