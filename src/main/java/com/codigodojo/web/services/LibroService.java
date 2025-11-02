package com.codigodojo.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codigodojo.web.models.Autor;
import com.codigodojo.web.models.Libro;
import com.codigodojo.web.repositories.AutorRepository;
import com.codigodojo.web.repositories.LibroRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroService {
	
	private static LibroRepository libroRepository = null;
    private AutorRepository autorRepository = null;

    @Autowired
    public void LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    // Guardar un nuevo autor
    @Transactional
    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }
    
    @Transactional(rollbackFor = Exception.class) 
    public Libro registrarAutorYLibro(String nombreAutor, String tituloLibro, int anio) { 
        // 1. Registrar Autor
        Autor autor = new Autor();
        autor.setNombre(nombreAutor);
        Autor autorGuardado = autorRepository.save(autor);

        // 2. Registrar Libro asociado (con validación implícita por el ORM)
        Libro libro = new Libro();
        libro.setTitulo(tituloLibro);
        libro.setAnioPublicacion(anio);
        libro.setAutor(autorGuardado); // Asociación

        // *Simulación de un error a mitad del proceso:*
        // La prueba usa un título que contiene esta cadena para forzar el fallo y el rollback.
        if (tituloLibro == null || tituloLibro.contains("FALLO_SIMULADO")) {
            // Se lanza una RuntimeException. Spring detecta esto y ejecuta el ROLLBACK.
            throw new RuntimeException("Error simulado: No se pudo guardar el libro, forzando rollback.");
        }

        return libroRepository.save(libro);
    }

    // Consultar todos los libros con sus autores
    public static List<Libro> consultarTodosLibrosConAutores() {
        // findallWithAuthor - se podría usar @EntityGraph o JOIN FETCH en un @Query
        return libroRepository.findAll();
    }

    // Eliminar un libro
    @Transactional
    public void eliminarLibro(Long idLibro) {
        libroRepository.deleteById(idLibro);
    }

    // Actualizar el nombre de un autor
    @Transactional
    public Autor actualizarNombreAutor(Long idAutor, String nuevoNombre) {
        Autor autor = autorRepository.findById(idAutor)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        autor.setNombre(nuevoNombre);
        return autorRepository.save(autor); // .save() para actualizar
    }

}
