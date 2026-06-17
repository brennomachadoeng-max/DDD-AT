package com.DDD.AT.assinatura.repository;

import com.DDD.AT.assinatura.modal.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {

    Optional<Assinatura> findByUsuarioIdAndAtivaTrue(Long usuarioId);

    List<Assinatura> findByUsuarioId(Long usuarioId);
}