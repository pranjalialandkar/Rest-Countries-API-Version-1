package com.example.restapi.restintegration.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.restintegration.business.CountriesService;
import com.example.restapi.restintegration.dto.CountryAreaDto;
import com.example.restapi.restintegration.dto.CountryLaguagesDto;
import com.example.restapi.restintegration.dto.CountryPopulationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/api")
public class CountriesController {

	private static final String String = null;
	private CountriesService countriesService;

	public CountriesController(CountriesService countriesService) {
		super();
		this.countriesService = countriesService;
	}

		
	@GetMapping("/countries/name/{name}")
	public List<Map<String, Object>> getCountryDetailsByName(@PathVariable String name){
			return countriesService.getCountryByName(name);
	}

	@GetMapping("/countries/names/population")
	public ResponseEntity<Page<CountryPopulationDto>> getCountryNamesAndPopulation(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "DESC") String sort) throws JsonMappingException, JsonProcessingException {

		Page<CountryPopulationDto> countryPopulationPage = countriesService.getCountryByPopulation(page, size, sort);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Page-Number", String.valueOf(countryPopulationPage.getNumber()));
		headers.add("X-Page-Size", String.valueOf(countryPopulationPage.getSize()));

		return ResponseEntity.ok().headers(headers).body(countryPopulationPage);
		
				
	}
	
	@GetMapping("/countries/names/area")
	public ResponseEntity<Page<CountryAreaDto>> getCountryNamesAndArea(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "DESC") String sort) throws JsonMappingException, JsonProcessingException {

		Page<CountryAreaDto> countryAreaPage = countriesService.getCountryByArea(page, size, sort);;

		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Page-Number", String.valueOf(countryAreaPage.getNumber()));
		headers.add("X-Page-Size", String.valueOf(countryAreaPage.getSize()));

		return ResponseEntity.ok().headers(headers).body(countryAreaPage);
		
				
	}
	
	@GetMapping("/countries/names/languages")
	public ResponseEntity<Page<CountryLaguagesDto>> getCountryNamesByLanguage(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "DESC") String sort) throws JsonMappingException, JsonProcessingException {

		Page<CountryLaguagesDto> countryLanguagesPage = countriesService.getCountryByLanguages(page, size, sort);

		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Page-Number", String.valueOf(countryLanguagesPage.getNumber()));
		headers.add("X-Page-Size", String.valueOf(countryLanguagesPage.getSize()));

		return ResponseEntity.ok().headers(headers).body(countryLanguagesPage);
		
				
	}

}
