package com.example.biblioteca_api.controller;

import org.springframework.web.bind.annotation.*;
import com.example.biblioteca_api.model.Emprestimo;
import com.example.biblioteca_api.service.EmprestimoService;
import com.example.biblioteca_api.exception.BusinessException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @GetMapping("/busca")
    public List<Emprestimo> buscarPorIntervalo(@RequestParam String inicio, @RequestParam String fim) {
        try {
            LocalDateTime dtInicio = LocalDateTime.parse(inicio);
            LocalDateTime dtFim = LocalDateTime.parse(fim);
            return service.buscarPorIntervalo(dtInicio, dtFim);
        } catch (DateTimeParseException e) {
            throw new BusinessException("Formato de data inválido. Use yyyy-MM-ddTHH:mm:ss");
        }
    }

    public static class EmprestimoRequest {
        public Long usuarioId;
        public List<Long> idsExemplares;
        public String dataPrevista;
    }

    @PostMapping
    public Emprestimo criarEmprestimo(@RequestBody EmprestimoRequest req) {
        LocalDateTime dataPrevista = req.dataPrevista != null ? LocalDateTime.parse(req.dataPrevista) : null;
        return service.realizarEmprestimo(req.usuarioId, req.idsExemplares, dataPrevista);
    }

    @PostMapping("/{id}/devolver")
    public Emprestimo devolver(@PathVariable Long id) {
        return service.registrarDevolucao(id);
    }
}
