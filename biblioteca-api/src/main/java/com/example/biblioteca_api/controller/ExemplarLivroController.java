package com.example.biblioteca_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.biblioteca_api.model.ExemplarLivro;
import com.example.biblioteca_api.repository.ExemplarLivroRepository;

@RestController
@RequestMapping("/exemplares")
public class ExemplarLivroController {
    private final ExemplarLivroRepository repository;

    public ExemplarLivroController(ExemplarLivroRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ExemplarLivro> listar() {
        return repository.findAll();
    }

    @PostMapping
    public ExemplarLivro criar(@RequestBody ExemplarLivro exemplar) {
        return repository.save(exemplar);
    }

    @PutMapping("/{id}")
    public ExemplarLivro atualizar(@PathVariable Long id, @RequestBody ExemplarLivro exemplar) {
        exemplar.setId(id);
        return repository.save(exemplar);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/quantidade/{livroId}")
    public String quantidade(@PathVariable Long livroId) {
        long disponiveis = repository.countByLivroIdAndStatus(livroId, "disponivel");
        long emprestados = repository.countByLivroIdAndStatus(livroId, "emprestado");
        long reservados = repository.countByLivroIdAndStatus(livroId, "reservado");

        return "Disponíveis: " + disponiveis +
                ", Emprestados: " + emprestados +
                ", Reservados: " + reservados;
    }
}
