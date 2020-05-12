package com.software.game.repository.impl;

import org.springframework.stereotype.Component;

import com.software.game.model.GameUsuario;
import com.software.game.repository.GameUsuarioRepository;

@Component
public class GameUsuarioRepositoryImpl implements GameUsuarioRepository {

	public GameUsuario save(GameUsuario usuario) {
		return GameUsuario.startGame(usuario);
	}

	public GameUsuario findByUsuario() {
		return GameUsuario.loadGame();
	}

}
