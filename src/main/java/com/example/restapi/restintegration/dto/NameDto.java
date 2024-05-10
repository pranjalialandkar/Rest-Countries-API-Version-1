package com.example.restapi.restintegration.dto;

import java.util.Map;

public class NameDto {

	private String common;
	private String official; 
	Map<String, NativeNameDto> nativeName;

	public String getCommon() {
		return common;
	}

	public void setCommon(String value) {
		this.common = value;
	}

	public String getOfficial() {
		return official;
	}

	public void setOfficial(String value) {
		this.official = value;
	}

	public Map<String, NativeNameDto> getNativeName() {
		return nativeName;
	}

	public void setNativeName(Map<String, NativeNameDto> nativeName) {
		this.nativeName = nativeName;
	}

}


