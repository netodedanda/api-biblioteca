package com.example.biblioteca_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.biblioteca_api.model.Livro;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutorId(Long autorId);
}
