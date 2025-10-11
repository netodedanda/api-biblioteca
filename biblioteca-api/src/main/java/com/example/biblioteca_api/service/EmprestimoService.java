package com.example.biblioteca_api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.biblioteca_api.repository.*;
import com.example.biblioteca_api.model.*;
import com.example.biblioteca_api.exception.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepo;
    private final ExemplarLivroRepository exemplarRepo;
    private final UsuarioRepository usuarioRepo;

    public EmprestimoService(EmprestimoRepository emprestimoRepo,
                             ExemplarLivroRepository exemplarRepo,
                             UsuarioRepository usuarioRepo) {
        this.emprestimoRepo = emprestimoRepo;
        this.exemplarRepo = exemplarRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Transactional
    public Emprestimo realizarEmprestimo(Long usuarioId, List<Long> idsExemplares, LocalDateTime dataPrevista) {
        UsuarioBiblioteca usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        List<ExemplarLivro> exemplares = idsExemplares.stream()
                .map(id -> exemplarRepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Exemplar " + id + " não encontrado")))
                .collect(Collectors.toList());

        boolean algumIndisponivel = exemplares.stream().anyMatch(e -> !e.isDisponivel());
        if (algumIndisponivel) {
            throw new BusinessException("Apenas exemplares disponíveis podem ser emprestados");
        }

        exemplares.forEach(e -> {
            e.setDisponivel(false);
            exemplarRepo.save(e);
        });

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setExemplares(exemplares);
        emprestimo.setDataHoraEmprestimo(LocalDateTime.now());
        emprestimo.setDataHoraDevolucaoPrevista(dataPrevista);

        return emprestimoRepo.save(emprestimo);
    }

    @Transactional
    public Emprestimo registrarDevolucao(Long idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepo.findById(idEmprestimo)
                .orElseThrow(() -> new ResourceNotFoundException("Empréstimo não encontrado"));

        emprestimo.setDataHoraDevolucaoReal(LocalDateTime.now());
        emprestimo.getExemplares().forEach(e -> {
            e.setDisponivel(true);
            exemplarRepo.save(e);
        });

        return emprestimoRepo.save(emprestimo);
    }

    public List<Emprestimo> buscarPorIntervalo(LocalDateTime inicio, LocalDateTime fim) {
        return emprestimoRepo.findByDataHoraEmprestimoBetween(inicio, fim);
    }
}
