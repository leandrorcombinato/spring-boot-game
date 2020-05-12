package com.software.game.util;

import org.springframework.stereotype.Component;

@Component
public class ResultService {
	
	int code;
	String clazz;
	String message;
	String detail;

	public ResultService() {
		super();
	}

	public ResultService(int code, String clazz, String message, String detail) {
		super();
		this.code = code;
		this.clazz = clazz;
		this.message = message;
		this.detail = detail;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "ResultService [code=" + code + ", clazz=" + clazz + ", message=" + message + ", detail=" + detail + "]";
	}
}
