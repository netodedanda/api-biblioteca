package com.example.biblioteca_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHoraEmprestimo;
    private LocalDateTime dataHoraDevolucaoPrevista;
    private LocalDateTime dataHoraDevolucaoReal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private UsuarioBiblioteca usuario;

    @OneToMany
    @JoinColumn(name = "emprestimo_id")
    private List<ExemplarLivro> exemplares;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataHoraEmprestimo() { return dataHoraEmprestimo; }
    public void setDataHoraEmprestimo(LocalDateTime dataHoraEmprestimo) { this.dataHoraEmprestimo = dataHoraEmprestimo; }

    public LocalDateTime getDataHoraDevolucaoPrevista() { return dataHoraDevolucaoPrevista; }
    public void setDataHoraDevolucaoPrevista(LocalDateTime dataHoraDevolucaoPrevista) { this.dataHoraDevolucaoPrevista = dataHoraDevolucaoPrevista; }

    public LocalDateTime getDataHoraDevolucaoReal() { return dataHoraDevolucaoReal; }
    public void setDataHoraDevolucaoReal(LocalDateTime dataHoraDevolucaoReal) { this.dataHoraDevolucaoReal = dataHoraDevolucaoReal; }

    public UsuarioBiblioteca getUsuario() { return usuario; }
    public void setUsuario(UsuarioBiblioteca usuario) { this.usuario = usuario; }

    public List<ExemplarLivro> getExemplares() { return exemplares; }
    public void setExemplares(List<ExemplarLivro> exemplares) { this.exemplares = exemplares; }
}
