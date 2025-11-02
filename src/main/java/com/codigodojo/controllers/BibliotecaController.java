package com.codigodojo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codigodojo.web.services.LibroService;

public class BibliotecaController {
	
	@Autowired
    private LibroService libroServiceJpa;

    @GetMapping("/web/libros")
    public String listarLibros(Model model) {
        
        // 1. Llama al servicio para obtener la lista de objetos Libro (Modelo)
        model.addAttribute("libros", LibroService.consultarTodosLibrosConAutores());
        
        // 2. Retorna el nombre lógico de la vista (Spring buscará: /WEB-INF/jsp/listaLibros.jsp)
        return "listaLibros";
    }

}
