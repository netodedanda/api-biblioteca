package com.example.biblioteca_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.biblioteca_api.model.Emprestimo;
import java.time.LocalDateTime;
import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByDataHoraEmprestimoBetween(LocalDateTime inicio, LocalDateTime fim);
}
