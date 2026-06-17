package com.DDD.AT.usuario.service;

import com.DDD.AT.usuario.dto.UsuarioRequest;
import com.DDD.AT.usuario.dto.UsuarioResponse;
import com.DDD.AT.usuario.modal.Usuario;
import com.DDD.AT.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse criarConta(UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRequestParaUsuario(usuarioRequest);
        return usuarioParaUsuarioResponse(usuarioRepository.save(usuario));
    }

    public UsuarioResponse buscarConta(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).map(this::usuarioParaUsuarioResponse).orElse(null);
    }

    public UsuarioResponse usuarioParaUsuarioResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail().getValor(),
                usuario.getDataNascimento().getData()
        );
    }

    public Usuario usuarioRequestParaUsuario(UsuarioRequest usuarioRequest) {
        return new Usuario(usuarioRequest.getNome(), usuarioRequest.getEmail(),
                usuarioRequest.getSenha(), usuarioRequest.getDataNascimento(),
                usuarioRequest.getCpf());
    }
}
