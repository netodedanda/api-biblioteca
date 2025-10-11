package com.example.biblioteca_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.biblioteca_api.model.UsuarioBiblioteca;

public interface UsuarioRepository extends JpaRepository<UsuarioBiblioteca, Long> {}
