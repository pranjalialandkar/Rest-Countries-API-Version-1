package com.example.restapi.restintegration.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ApiService {

	ResponseEntity<String> getFilteredDataResponse(String filter);

	ResponseEntity<List> getVariableDataResponse(String name);
}
