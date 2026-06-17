package com.DDD.AT.usuario.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Getter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Embedded
    private Email email;

    @Embedded
    private Senha senha;

    @Embedded
    private DataNascimento dataNascimento;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @Embedded
    private Cpf cpf;

    public Usuario(String nome, String email, String senha, LocalDate dataNascimento, String cpf) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório.");

        this.nome = nome.trim();
        this.email = new Email(email);
        this.senha = new Senha(senha);
        this.dataNascimento = new DataNascimento(dataNascimento);
        this.cpf = new Cpf(cpf);
        this.dataCadastro = LocalDate.now();
    }
}