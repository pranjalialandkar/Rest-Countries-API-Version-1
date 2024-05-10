package com.example.restapi.restintegration.dto;

import java.util.Map;

public class CountryLaguagesDto {
	private NameDto name;
	private Map<String, String> Languages;
	public NameDto getName() {
		return name;
	}
	public void setName(NameDto name) {
		this.name = name;
	}
	public Map<String, String> getLanguages() {
		return Languages;
	}
	public void setLanguages(Map<String, String> languages) {
		Languages = languages;
	}
	
	

}
