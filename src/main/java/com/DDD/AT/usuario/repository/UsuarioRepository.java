package com.DDD.AT.usuario.repository;


import com.DDD.AT.usuario.modal.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
