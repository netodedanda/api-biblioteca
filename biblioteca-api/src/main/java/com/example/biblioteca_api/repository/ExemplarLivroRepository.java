package com.example.biblioteca_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.biblioteca_api.model.ExemplarLivro;

public interface ExemplarLivroRepository extends JpaRepository<ExemplarLivro, Long> {
    Long countByLivroIdAndStatus(Long livroId, String status);
}
