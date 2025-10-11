package com.example.biblioteca_api.controller;

import org.springframework.web.bind.annotation.*;
import com.example.biblioteca_api.model.UsuarioBiblioteca;
import com.example.biblioteca_api.repository.UsuarioRepository;
import com.example.biblioteca_api.exception.ResourceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository repo;

    public UsuarioController(UsuarioRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<UsuarioBiblioteca> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public UsuarioBiblioteca buscar(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    @PostMapping
    public UsuarioBiblioteca criar(@RequestBody UsuarioBiblioteca usuario) {
        return repo.save(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioBiblioteca atualizar(@PathVariable Long id, @RequestBody UsuarioBiblioteca u) {
        UsuarioBiblioteca existente = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        existente.setNome(u.getNome());
        existente.setEmail(u.getEmail());
        return repo.save(existente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
