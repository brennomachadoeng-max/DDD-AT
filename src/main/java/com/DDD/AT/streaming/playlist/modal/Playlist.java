package com.DDD.AT.streaming.playlist.modal;

import com.DDD.AT.streaming.musica.modal.Musica;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlists")
@Getter
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "is_favorito", nullable = false)
    private boolean favorito;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "playlist_musicas", joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "musica_id"))
    private List<Musica> musicas = new ArrayList<>();

    public Playlist(String nome, Long usuarioId) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("O nome da playlist não pode ser vazio.");
        if (usuarioId == null) throw new IllegalArgumentException("A playlist deve pertencer a um usuário válido.");

        this.nome = nome.trim();
        this.usuarioId = usuarioId;
        this.favorito = false;
    }


    public static Playlist criarPlaylistFavoritos(Long usuarioId) {
        if (usuarioId == null) throw new IllegalArgumentException("A playlist deve pertencer a um usuário válido.");

        Playlist favoritos = new Playlist();
        favoritos.nome = "Músicas Curtidas"; // Nome padrão estilo Spotify
        favoritos.usuarioId = usuarioId;
        favoritos.favorito = true; // Flag ativada
        return favoritos;
    }


    public void adicionarMusica(Musica musica) {
        if (musica == null) throw new IllegalArgumentException("Não é possível adicionar uma música nula.");
        if (this.musicas.contains(musica)) {
            throw new IllegalStateException("Esta música já está nesta playlist.");
        }
        this.musicas.add(musica);
    }

    public void removerMusica(Musica musica) {
        if (musica == null) throw new IllegalArgumentException("Música inválida.");
        this.musicas.remove(musica);
    }
}