package com.DDD.AT.financeiro.cartaoCredito.repository;

import com.DDD.AT.financeiro.cartaoCredito.modal.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {
    List<CartaoCredito> findByUsuarioId(Long usuarioId);
}
