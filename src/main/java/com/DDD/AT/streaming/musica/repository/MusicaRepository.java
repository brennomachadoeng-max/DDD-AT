package com.DDD.AT.streaming.musica.repository;

import com.DDD.AT.streaming.musica.modal.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
