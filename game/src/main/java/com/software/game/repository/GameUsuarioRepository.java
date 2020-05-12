package com.software.game.repository;

import org.springframework.stereotype.Repository;

import com.software.game.model.GameUsuario;

@Repository
public interface GameUsuarioRepository {

	GameUsuario save(GameUsuario usuario);

	GameUsuario findByUsuario();
}
