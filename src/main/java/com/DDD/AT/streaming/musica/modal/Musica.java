package com.DDD.AT.streaming.musica.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "musicas")
@Getter
@NoArgsConstructor
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String artista;

    @Embedded
    private Duracao duracao;

    public Musica(String titulo, String artista, Integer duracaoSegundos) {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("O título da música é obrigatório.");
        if (artista == null || artista.isBlank()) throw new IllegalArgumentException("O artista da música é obrigatório.");

        this.titulo = titulo.trim();
        this.artista = artista.trim();
        this.duracao = new Duracao(duracaoSegundos);
    }
}