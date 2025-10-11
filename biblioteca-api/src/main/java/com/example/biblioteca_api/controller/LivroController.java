package com.example.biblioteca_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.biblioteca_api.model.Livro;
import com.example.biblioteca_api.repository.LivroRepository;
import com.example.biblioteca_api.repository.AutorRepository;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroController(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @GetMapping
    public List<Livro> listar() {
        return livroRepository.findAll();
    }

    @GetMapping("/autor/{autorId}")
    public List<Livro> listarPorAutor(@PathVariable Long autorId) {
        return livroRepository.findByAutorId(autorId);
    }

    @PostMapping
    public Livro criar(@RequestBody Livro livro) {
        if (livro.getAutor() != null && livro.getAutor().getId() != null) {
            autorRepository.findById(livro.getAutor().getId())
                    .ifPresent(livro::setAutor);
        }
        return livroRepository.save(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable Long id, @RequestBody Livro livro) {
        livro.setId(id);
        if (livro.getAutor() != null && livro.getAutor().getId() != null) {
            autorRepository.findById(livro.getAutor().getId())
                    .ifPresent(livro::setAutor);
        }
        return livroRepository.save(livro);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        livroRepository.deleteById(id);
    }
}
