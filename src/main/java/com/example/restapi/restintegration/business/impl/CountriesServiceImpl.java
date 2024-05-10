package com.example.restapi.restintegration.business.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.restapi.restintegration.api.service.ApiService;

import com.example.restapi.restintegration.business.CountriesService;
import com.example.restapi.restintegration.dto.CountryAreaDto;
import com.example.restapi.restintegration.dto.CountryLaguagesDto;
import com.example.restapi.restintegration.dto.CountryPopulationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CountriesServiceImpl implements CountriesService {

	private ApiService apiService;

	public CountriesServiceImpl(ApiService apiService) {
		super();
		this.apiService = apiService;
	}

	
	@Override
	public Page<CountryPopulationDto> getCountryByPopulation(int page, int size, String sort)
			throws JsonMappingException, JsonProcessingException {

		ResponseEntity<String> response = apiService.getFilteredDataResponse("population");
		List<CountryPopulationDto> list = Arrays
				.asList(new ObjectMapper().readValue(response.getBody(), CountryPopulationDto[].class));

		if (sort.equalsIgnoreCase("ASC")) {
			list.sort(Comparator.comparing(CountryPopulationDto::getPopulation, (s1, s2) -> {
				return s1.compareTo(s2);
			}));
		} else if (sort.equalsIgnoreCase("DESC")) {
			list.sort(Comparator.comparing(CountryPopulationDto::getPopulation, (s1, s2) -> {
				return s2.compareTo(s1);
			}));
		}
		Pageable pageRequest = PageRequest.of(page, size);
		int start = (int) pageRequest.getOffset();
		int end = Math.min((start + pageRequest.getPageSize()), list.size());

		List<CountryPopulationDto> pageContent = list.subList(start, end);
		return new PageImpl<CountryPopulationDto>(pageContent, pageRequest, list.size());
	}

	@Override
	public Page<CountryAreaDto> getCountryByArea(int page, int size, String sort) throws JsonMappingException, JsonProcessingException {

		ResponseEntity<String> response = apiService.getFilteredDataResponse("area");
		List<CountryAreaDto> list = Arrays
				.asList(new ObjectMapper().readValue(response.getBody(), CountryAreaDto[].class));

		if (sort.equalsIgnoreCase("ASC")) {
			list.sort(Comparator.comparing(CountryAreaDto::getArea, (s1, s2) -> {
				return s1.compareTo(s2);
			}));
		} else if (sort.equalsIgnoreCase("DESC")) {
			list.sort(Comparator.comparing(CountryAreaDto::getArea, (s1, s2) -> {
				return s2.compareTo(s1);
			}));
		}
		Pageable pageRequest = PageRequest.of(page, size);
		int start = (int) pageRequest.getOffset();
		int end = Math.min((start + pageRequest.getPageSize()), list.size());

		List<CountryAreaDto> pageContent = list.subList(start, end);
		return new PageImpl<CountryAreaDto>(pageContent, pageRequest, list.size());
	}

	@Override
	public Page<CountryLaguagesDto> getCountryByLanguages(int page, int size, String sort)
			throws JsonMappingException, JsonProcessingException {

		ResponseEntity<String> response = apiService.getFilteredDataResponse("languages");
		List<CountryLaguagesDto> list = Arrays
				.asList(new ObjectMapper().readValue(response.getBody(), CountryLaguagesDto[].class));
		Pageable pageRequest = PageRequest.of(page, size);
		int start = (int) pageRequest.getOffset();
		int end = Math.min((start + pageRequest.getPageSize()), list.size());

		List<CountryLaguagesDto> pageContent = list.subList(start, end);
		return new PageImpl<CountryLaguagesDto>(pageContent, pageRequest, list.size());
	}

	@Override
	public List<Map<String, Object>> getCountryByName(String name) {
		
		return apiService.getVariableDataResponse(name).getBody();
	}
	
	
}
