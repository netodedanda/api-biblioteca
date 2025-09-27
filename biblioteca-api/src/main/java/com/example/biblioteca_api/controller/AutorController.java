package com.example.biblioteca_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.biblioteca_api.model.Autor;
import com.example.biblioteca_api.repository.AutorRepository;

@RestController
@RequestMapping("/autores")
public class AutorController {
    private final AutorRepository repository;

    public AutorController(AutorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Autor> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Autor criar(@RequestBody Autor autor) {
        return repository.save(autor);
    }

    @PutMapping("/{id}")
    public Autor atualizar(@PathVariable Long id, @RequestBody Autor autor) {
        autor.setId(id);
        return repository.save(autor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
