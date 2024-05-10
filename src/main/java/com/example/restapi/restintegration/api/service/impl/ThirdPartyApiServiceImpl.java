package com.example.restapi.restintegration.api.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.restapi.restintegration.api.service.ApiService;
import com.example.restapi.restintegration.business.impl.CountryNotFoundException;


@Service
public class ThirdPartyApiServiceImpl implements ApiService {

	String baseUrl = "https://restcountries.com/v3.1/";

	String getAll = "/all";

	String getDetailsByName = "/name/";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<String> getFilteredDataResponse(String filter) {
		HttpEntity<Void> httpEntity = new HttpEntity<>(gethttpHeaders());
		String url = new StringBuilder(baseUrl).append(getAll).append("?fields=name").append(",").append(filter)
				.toString();
		return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
	}

	@Override
	public ResponseEntity<List> getVariableDataResponse(String name) {
		
		HttpEntity <Void> httpEntity = new HttpEntity<>(gethttpHeaders()); 
		String url = new StringBuilder(baseUrl).append(getDetailsByName).append(name).toString();
		  ResponseEntity<List> response = null; 
		  try { 
			  response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class); 
			  
		  }catch(HttpClientErrorException httpClientException) { 
			  throw new
				  CountryNotFoundException(httpClientException.getStatusCode().toString()
				  ,"No Such a Country Found. Please send correct country name."); 
			  
		  }
		  return response;
	}
		

	private HttpHeaders gethttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
