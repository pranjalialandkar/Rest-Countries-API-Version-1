package com.example.restapi.restintegration.dto;

public class CountryNameDto {
	private NameDto name;

	public NameDto getName() {
		return name;
	}

	public void setName(NameDto name) {
		this.name = name;
	}
	
	public String getCommonName() {
		return this.name.getCommon();
	}

}
