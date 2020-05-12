package com.software.game.controller;

import java.io.FileNotFoundException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.software.game.business.GameUsuarioBusiness;
import com.software.game.enumeration.EnumSystem;
import com.software.game.model.GameUsuario;
import com.software.game.service.GameUsuarioService;
import com.software.game.util.ResultService;

@RestController
public class GameUsuarioController {

    private final Logger logger = LoggerFactory.getLogger(GameUsuarioController.class);

	@Autowired
	private Environment env;

	@Autowired
	private GameUsuarioService service;
	
	@Autowired
	private GameUsuarioBusiness business;
	
	@PostConstruct
	public void initGame() throws FileNotFoundException {
		URL jsonFile = GameUsuarioController.class.getResource("/data/player.json");
		business.initStartGame(jsonFile);
	}

	@PostMapping(value = "/game")
	public ResponseEntity<ResultService> save(@RequestBody GameUsuario usuario) {
		try {
			service.save(usuario);
			logger.info(env.getProperty("service.message.add"));
			return business.mensagemCreate(usuario);

		} catch (RuntimeException e) {
			logger.debug(env.getProperty("service.message.system.internal.error"));
			return business.mensagemErroInternoSistema(usuario, e);
		}
	}

	@GetMapping(value = "/game")
	public @ResponseBody ResponseEntity<ResultService> findByUsuario() throws FileNotFoundException {
		GameUsuario usuario = service.findByUsuario();
		if (usuario  != null) {
			logger.info(usuario.toString() + env.getProperty("game.message.found"));
			return business.mensagemSucesso(usuario);

		} else {
			logger.info(EnumSystem.ENTITY_NULL.name() + env.getProperty("game.message.notfound"));
			return business.mensagemNaoEncontrado(usuario);
		}
	}

}
