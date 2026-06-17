package com.DDD.AT.assinatura.repository;

import com.DDD.AT.assinatura.modal.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {

    // Busca se existe uma assinatura ativa para o usuário
    Optional<Assinatura> findByUsuarioIdAndAtivaTrue(Long usuarioId);

    // CORRIGIDO: Alterado de List<String> para List<Assinatura>
    List<Assinatura> findByUsuarioId(Long usuarioId);
}