package com.software.game.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.software.game.GameApplication;

public class GameUsuario implements Serializable {

	private static final long serialVersionUID = -939899503023168582L;

	private String namePlayerOne;
	
	private String namePlayerTwo;
	
	private String namePlayerThree;
	
	private Integer pointsPlayerOne;
	
	private Integer pointsPlayerTwo;
	
	private Integer pointsPlayerThree;
	
	private String playerWonGame;
	
	public GameUsuario() {
		super();
	}
	
	public GameUsuario(String namePlayerOne, String namePlayerTwo, String namePlayerThree, Integer pointsPlayerOne,
			Integer pointsPlayerTwo, Integer pointsPlayerThree, String playerWonGame) {
		super();
		this.namePlayerOne = namePlayerOne;
		this.namePlayerTwo = namePlayerTwo;
		this.namePlayerThree = namePlayerThree;
		this.pointsPlayerOne = pointsPlayerOne;
		this.pointsPlayerTwo = pointsPlayerTwo;
		this.pointsPlayerThree = pointsPlayerThree;
		this.playerWonGame = playerWonGame;
	}

	public String getNamePlayerOne() {
		return namePlayerOne;
	}

	public void setNamePlayerOne(String namePlayerOne) {
		this.namePlayerOne = namePlayerOne;
	}

	public String getNamePlayerTwo() {
		return namePlayerTwo;
	}

	public void setNamePlayerTwo(String namePlayerTwo) {
		this.namePlayerTwo = namePlayerTwo;
	}

	public String getNamePlayerThree() {
		return namePlayerThree;
	}

	public void setNamePlayerThree(String namePlayerThree) {
		this.namePlayerThree = namePlayerThree;
	}
	
	public Integer getPointsPlayerOne() {
		return pointsPlayerOne;
	}

	public void setPointsPlayerOne(Integer pointsPlayerOne) {
		this.pointsPlayerOne = pointsPlayerOne;
	}

	public Integer getPointsPlayerTwo() {
		return pointsPlayerTwo;
	}

	public void setPointsPlayerTwo(Integer pointsPlayerTwo) {
		this.pointsPlayerTwo = pointsPlayerTwo;
	}

	public Integer getPointsPlayerThree() {
		return pointsPlayerThree;
	}

	public void setPointsPlayerThree(Integer pointsPlayerThree) {
		this.pointsPlayerThree = pointsPlayerThree;
	}

	public String getPlayerWonGame() {
		return playerWonGame;
	}

	public void setPlayerWonGame(String playerWonGame) {
		this.playerWonGame = playerWonGame;
	}

	// converte objetos Java para JSON e retorna JSON como String
	public static String converterFromJson(GameUsuario contato) {
		Gson gson = new Gson();
		return gson.toJson(contato);
	}
	
	// converte string JSON para objeto Java
	public static GameUsuario converterFromEntity(String jsonString) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		if(jsonString.contains("[")){
			return gson.fromJson(jsonString.replace("[", "").replace("]", ""), GameUsuario.class);
		}else{
			return gson.fromJson(jsonString, GameUsuario.class);
		}
	}
	
	public static GameUsuario startGame(GameUsuario usuario) {
		
		URL startGame = GameApplication.class.getClassLoader().getResource(".");
				
		try (OutputStream output = new FileOutputStream(startGame.getFile().concat("data/play-game.properties"))) {

            Properties prop = new Properties();

            // set the properties value 
            prop.setProperty("jogador1", usuario.getNamePlayerOne() != null ? usuario.getNamePlayerOne() : "");
            prop.setProperty("pontos.jogador1", usuario.getPointsPlayerOne() == null ? "0" : usuario.getPointsPlayerOne().toString());
            prop.setProperty("jogador2", usuario.getNamePlayerTwo() != null ? usuario.getNamePlayerTwo() : "");
            prop.setProperty("pontos.jogador2", usuario.getPointsPlayerTwo() == null ? "0" : usuario.getPointsPlayerTwo().toString());
            prop.setProperty("jogador3", usuario.getNamePlayerThree() != null ? usuario.getNamePlayerThree() : "");
            prop.setProperty("pontos.jogador3", usuario.getPointsPlayerThree() == null ? "0" : usuario.getPointsPlayerThree().toString());
            prop.setProperty("vencedor.jogo", usuario.getPlayerWonGame() != null ? usuario.getPlayerWonGame() : "");

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }
		return usuario;
	}
	
	public static GameUsuario loadGame() {
		GameUsuario usuario = new GameUsuario(); 

		URL loadGame = GameApplication.class.getClassLoader().getResource(".");
		
		try (InputStream input = new FileInputStream(loadGame.getFile().concat("data/play-game.properties"))) {
	
	        Properties prop = new Properties();
	
	        // load a properties file
	        prop.load(input);
	
	        // get the property value and print it out
	        usuario.setNamePlayerOne(prop.getProperty("jogador1"));
	        usuario.setPointsPlayerOne(Integer.parseInt(prop.getProperty("pontos.jogador1")));
	        usuario.setNamePlayerTwo(prop.getProperty("jogador2"));
	        usuario.setPointsPlayerTwo(Integer.parseInt(prop.getProperty("pontos.jogador2")));
	        usuario.setNamePlayerThree(prop.getProperty("jogador3"));
	        usuario.setPointsPlayerThree(Integer.parseInt(prop.getProperty("pontos.jogador3")));
	        usuario.setPlayerWonGame(prop.getProperty("vencedor.jogo"));
	        
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
		return usuario;
	}

	@Override
	public String toString() {
		return " { namePlayerOne=" + namePlayerOne + ", namePlayerTwo=" + namePlayerTwo + ", namePlayerThree="
				+ namePlayerThree + ", pointsPlayerOne=" + pointsPlayerOne + ", pointsPlayerTwo=" + pointsPlayerTwo
				+ ", pointsPlayerThree=" + pointsPlayerThree + ", playerWonGame=" + playerWonGame + "}";
	}

}
