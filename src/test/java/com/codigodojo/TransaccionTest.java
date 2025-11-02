package com.codigodojo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codigodojo.web.repositories.AutorRepository;
import com.codigodojo.web.repositories.LibroRepository;
import com.codigodojo.web.services.LibroService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransaccionTest {
	
	@Autowired
    private LibroService libroService;

    @Autowired
    private AutorRepository autorRepository; // Para verificar el estado final

    @Autowired
    private LibroRepository libroRepository; // Para verificar el estado final

    @Test
    public void testTransaccionRollbackEnFallo() {
        // Preparación de datos
        String nombreAutor = "Autor Transaccional Fallido";
        String tituloLibro = "Libro con FALLO_SIMULADO"; // Activa el error simulado

        // Ejecutar la operación que debería fallar y hacer rollback
        assertThrows(RuntimeException.class, () -> {
            libroService.registrarAutorYLibro(nombreAutor, tituloLibro, 2024);
        }, "Se esperaba una RuntimeException por el fallo simulado.");

        // *Verificación de Rollback*
        // El autor no debe existir después del rollback
        long countAutores = autorRepository.count();
        long countLibros = libroRepository.count();
        
        // Asumiendo que la base de datos está inicialmente vacía para estas entidades.
        // Si no está vacía, se debe verificar que el autor con ese nombre no exista.
        
        // Mejor verificación: buscar si el autor se creó
        assertFalse(autorRepository.findAll().stream()
                .anyMatch(a -> a.getNombre().equals(nombreAutor)),
                "El autor NO debería existir tras el rollback de la transacción.");
        
        // Buscar si el libro se creó
        assertFalse(libroRepository.findByTitulo(tituloLibro).isEmpty(),
                "El libro NO debería existir tras el rollback de la transacción.");
    }

	

}
