package com.example.restapi.restintegration.business;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.example.restapi.restintegration.dto.CountryAreaDto;
import com.example.restapi.restintegration.dto.CountryLaguagesDto;
import com.example.restapi.restintegration.dto.CountryPopulationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


public interface CountriesService {
	
	Page<CountryPopulationDto> getCountryByPopulation(int page, int size, String sort)throws JsonMappingException, JsonProcessingException;

	Page<CountryAreaDto> getCountryByArea(int page, int size, String sort)throws JsonMappingException, JsonProcessingException;

	Page<CountryLaguagesDto> getCountryByLanguages(int page, int size, String sort)throws JsonMappingException, JsonProcessingException;

	List<Map<String, Object>> getCountryByName(String name);

}
