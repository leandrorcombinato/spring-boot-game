package com.software.game.service;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.game.model.GameUsuario;
import com.software.game.repository.impl.GameUsuarioRepositoryImpl;

@Service
public class GameUsuarioService { 
	
	@Autowired
  	GameUsuarioRepositoryImpl repositoryImpl;
	
	public GameUsuario save(GameUsuario usuario) {
		return repositoryImpl.save(usuario);
	}
	
	public GameUsuario findByUsuario() throws FileNotFoundException {
		return repositoryImpl.findByUsuario();
	}
}
