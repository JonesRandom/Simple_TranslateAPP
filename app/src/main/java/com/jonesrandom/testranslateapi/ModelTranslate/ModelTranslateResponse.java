package com.jonesrandom.testranslateapi.ModelTranslate;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelTranslateResponse {

	@SerializedName("code")
	@Expose
	private int code;

	@SerializedName("text")
	@Expose
	private List<String> text;

	@SerializedName("lang")
	@Expose
	private String lang;

	@SerializedName("message")
	@Expose
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setText(List<String> text){
		this.text = text;
	}

	public List<String> getText(){
		return text;
	}

	public String getTextTranslate(){
		return text.get(0);
	}

	public void setLang(String lang){
		this.lang = lang;
	}

	public String getLang(){
		return lang;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

}