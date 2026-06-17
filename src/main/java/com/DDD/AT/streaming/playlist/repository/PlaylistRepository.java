package com.DDD.AT.streaming.playlist.repository;

import com.DDD.AT.streaming.playlist.modal.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    @Query("SELECT p FROM Playlist p LEFT JOIN FETCH p.musicas WHERE p.id = :id")
    Optional<Playlist> encontrarPorIdComMusicas(@Param("id") Long id);
}