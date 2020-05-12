package com.software.game.enumeration;

public enum EnumSystem {

	ENTITY_NULL(0),
	SUCESSO(1), 
	CAMPOS_INVALIDOS(2), 
	NENHUMA_INFORMACAO_ENCONTRADA(3), 
	INFORMACAO_ENCONTRADA(4),
	ERRO_INTERNO_NO_SISTEMA(5);

	private int code;

	EnumSystem(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static String systemByDescricao(int code){
		for (EnumSystem sistema : EnumSystem.values()) { 
		    if(sistema.getCode() == code){
		    	return sistema.name();
		    }
		}
		return null;
	}

	public static int systemByNumero(int code){
		for (EnumSystem sistema : EnumSystem.values()) { 
		    if(sistema.getCode() == code){
		    	return sistema.getCode();
		    }
		}
		return 0;
	}

}
