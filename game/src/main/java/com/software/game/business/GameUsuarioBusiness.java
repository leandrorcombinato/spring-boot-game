package com.software.game.business;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.software.game.enumeration.EnumSystem;
import com.software.game.model.GameUsuario;
import com.software.game.util.ResultService;

@Component
public class GameUsuarioBusiness {
	
	@Autowired
	private Environment env;

	public ResponseEntity<ResultService> mensagemSucesso(GameUsuario usuario) {
		return new ResponseEntity<ResultService>(
			   	   new ResultService(EnumSystem.INFORMACAO_ENCONTRADA.ordinal(),
			   			 GameUsuario.converterFromJson(usuario),
			             env.getProperty("game.message.found"), 
			             EnumSystem.INFORMACAO_ENCONTRADA.name()),
			   HttpStatus.FOUND);
	}

	public ResponseEntity<ResultService> mensagemNaoEncontrado(@Valid GameUsuario usuario) {
		return new ResponseEntity<ResultService>(
			   	   new ResultService(EnumSystem.NENHUMA_INFORMACAO_ENCONTRADA.ordinal(),
			             null,
			             env.getProperty("game.message.notfound"), 
			             EnumSystem.ENTITY_NULL.name()),
			   HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResultService> mensagemOK(GameUsuario usuario) {
		return new ResponseEntity<ResultService>(
				   new ResultService(EnumSystem.SUCESSO.ordinal(),
						   GameUsuario.converterFromJson(usuario),
						   EnumSystem.SUCESSO.name(),
			               env.getProperty("service.message.delete")), 
				   HttpStatus.OK);
	}

	public ResponseEntity<ResultService> mensagemErroInternoSistema(GameUsuario usuario, RuntimeException e) {
		return new ResponseEntity<ResultService>(
				   new ResultService(EnumSystem.ERRO_INTERNO_NO_SISTEMA.ordinal(),
						   GameUsuario.converterFromJson(usuario),
						   env.getProperty("service.message.system.internal.error"),
						   e.toString()),
				   HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<ResultService> mensagemCreate(@Valid GameUsuario usuario) {
		return new ResponseEntity<ResultService>(
				   new ResultService(EnumSystem.SUCESSO.ordinal(),
						   GameUsuario.converterFromJson(usuario),
						   env.getProperty("service.message.add"), 
						   null),
				   HttpStatus.CREATED);
	}
	
	public GameUsuario initStartGame(URL jsonFile) throws FileNotFoundException {
		InputStreamReader is = new InputStreamReader(new FileInputStream(jsonFile.getPath()));
		Gson gson = new GsonBuilder().create();
		GameUsuario usuario = gson.fromJson(is, GameUsuario.class);
		return GameUsuario.startGame(usuario);
	}
}
