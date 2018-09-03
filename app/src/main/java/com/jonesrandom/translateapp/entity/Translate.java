package com.jonesrandom.translateapp.entity;

import java.util.List;

public class Translate {

	private int code;
	private List<String> text;

	public Translate(int code, List<String> text) {
		this.code = code;
		this.text = text;
	}

	public int getCode(){
		return code;
	}

	public String getText(){
		return text.get(0);
	}
}