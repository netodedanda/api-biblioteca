package com.example.biblioteca_api.model;

import jakarta.persistence.*;

@Entity
public class ExemplarLivro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status; // "disponivel", "emprestado", "reservado"

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    // -----------------------------
    // Getters e Setters padrão
    // -----------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    // -----------------------------
    // Métodos para disponibilidade
    // -----------------------------
    public boolean isDisponivel() {
        return "disponivel".equalsIgnoreCase(this.status);
    }

    public void setDisponivel(boolean disponivel) {
        this.status = disponivel ? "disponivel" : "emprestado";
    }
}
