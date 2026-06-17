package com.DDD.AT.shared.application.CadastroUsuarioOrquestrador.service;

import com.DDD.AT.streaming.playlist.service.PlaylistService;
import com.DDD.AT.usuario.dto.UsuarioRequest;
import com.DDD.AT.usuario.dto.UsuarioResponse;
import com.DDD.AT.usuario.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioOrquestradorService {

    private final UsuarioService usuarioService;
    private final PlaylistService playlistService;

    public CadastroUsuarioOrquestradorService(UsuarioService usuarioService, PlaylistService playlistService) {
        this.usuarioService = usuarioService;
        this.playlistService = playlistService;
    }

    public UsuarioResponse cadastrarUsuarioComFavoritos(UsuarioRequest request) {
        UsuarioResponse novoUsuario = usuarioService.criarConta(request);
        playlistService.inicializarFavoritos(novoUsuario.getId());
        return novoUsuario;
    }
}