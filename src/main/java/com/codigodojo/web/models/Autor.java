package com.codigodojo.web.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // El nombre del autor (solicitado en el ejercicio)
    @Column(nullable = false)
    private String nombre; 

    // Relación OneToMany: Un autor puede tener muchos libros.
    // 'mappedBy' apunta al campo 'autor' en la clase LibroJpa.
    // CascadeType.ALL significa que si eliminas un autor, sus libros asociados también se eliminan.
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private Set<Libro> libros; 

    // Constructor vacío (requerido por JPA)
    public Autor() {}

    // Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Libro> getLibros() {
		return libros;
	}

	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
	}
}