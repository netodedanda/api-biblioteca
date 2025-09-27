package com.example.biblioteca_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.biblioteca_api.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {}
