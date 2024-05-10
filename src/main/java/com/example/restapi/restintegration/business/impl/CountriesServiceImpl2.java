/*
 * package com.example.restapi.restintegration.business.impl;
 * 
 * import java.util.Arrays; import java.util.Comparator; import java.util.List;
 * import java.util.Map;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.data.domain.Page; import
 * org.springframework.data.domain.PageImpl; import
 * org.springframework.data.domain.PageRequest; import
 * org.springframework.data.domain.Pageable; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.http.MediaType; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Service; import
 * org.springframework.web.client.HttpClientErrorException; import
 * org.springframework.web.client.RestTemplate; import
 * org.springframework.http.HttpEntity;
 * 
 * import com.example.restapi.restintegration.business.CountriesService; import
 * com.example.restapi.restintegration.dto.CountryAreaDto; import
 * com.example.restapi.restintegration.dto.CountryLaguagesDto; import
 * com.example.restapi.restintegration.dto.CountryNameDto; import
 * com.example.restapi.restintegration.dto.CountryPopulationDto; import
 * com.fasterxml.jackson.core.JsonProcessingException; import
 * com.fasterxml.jackson.databind.JsonMappingException; import
 * com.fasterxml.jackson.databind.ObjectMapper;
 * 
 * @Service public class CountriesServiceImpl2 implements CountriesService {
 * 
 * String baseUrl = "https://restcountries.com/v3.1/";
 * 
 * String GetAll = "/all";
 * 
 * String CountryByName = "/name/";
 * 
 * @Autowired private RestTemplate restTemplate;
 * 
 * @Override public List<Map<String, Object>> getCountryByName(String name) {
 * HttpEntity<Void> httpEntity = new HttpEntity<>(gethttpHeaders()); String url
 * = new StringBuilder(baseUrl).append(CountryByName).append(name).toString();
 * ResponseEntity<List> response; try { response = restTemplate.exchange(url,
 * HttpMethod.GET, httpEntity, List.class); } catch (HttpClientErrorException
 * httpClientException) { throw new
 * CountryNotFoundException(httpClientException.getStatusCode().toString(),
 * "No Such a Country Found. Please send correct country name."); } return
 * response.getBody(); }
 * 
 * @Override public List<CountryPopulationDto>
 * getCountryNamesByPopulation(String sort) throws JsonMappingException,
 * JsonProcessingException { HttpEntity<Void> httpEntity = new
 * HttpEntity<>(gethttpHeaders()); String url = new
 * StringBuilder(baseUrl).append(GetAll).append("?fields=name,population").
 * toString(); ResponseEntity<String> response = restTemplate.exchange(url,
 * HttpMethod.GET, httpEntity, String.class); ObjectMapper mapper = new
 * ObjectMapper(); List<CountryPopulationDto> namePopulationList = Arrays
 * .asList(mapper.readValue(response.getBody(), CountryPopulationDto[].class));
 * //namePopulationList.sort(Comparator.comparing(CountryPopulationDto::
 * getPopulation)); if(sort.equalsIgnoreCase("ASC")){
 * namePopulationList.sort(Comparator.comparing(CountryPopulationDto::
 * getPopulation,(s1, s2) -> {return s1.compareTo(s2);})); }else {
 * namePopulationList.sort(Comparator.comparing(CountryPopulationDto::
 * getPopulation,(s1, s2) -> {return s2.compareTo(s1);})); } return
 * namePopulationList; }
 * 
 * @Override public List<CountryAreaDto> getCountryNamesByArea(String sort)
 * throws JsonMappingException, JsonProcessingException { HttpEntity<Void>
 * httpEntity = new HttpEntity<>(gethttpHeaders()); String url = new
 * StringBuilder(baseUrl).append(GetAll).append("?fields=name,area").toString();
 * ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,
 * httpEntity, String.class); ObjectMapper mapper = new ObjectMapper();
 * List<CountryAreaDto> nameAreaList =
 * Arrays.asList(mapper.readValue(response.getBody(), CountryAreaDto[].class));
 * // nameAreaList.sort(Comparator.comparing(CountryAreaDto::getArea)); if
 * (sort.equalsIgnoreCase("ASC")) {
 * nameAreaList.sort(Comparator.comparing(CountryAreaDto::getArea, (s1, s2) -> {
 * return s1.compareTo(s2); })); } else {
 * nameAreaList.sort(Comparator.comparing(CountryAreaDto::getArea, (s1, s2) -> {
 * return s2.compareTo(s1); })); }
 * 
 * return nameAreaList; }
 * 
 * @Override public List<CountryLaguagesDto> getCountryNamesByLanguages() throws
 * JsonMappingException, JsonProcessingException { HttpEntity<Void> httpEntity =
 * new HttpEntity<>(gethttpHeaders()); String url = new
 * StringBuilder(baseUrl).append(GetAll).append("?fields=name,languages").
 * toString(); ResponseEntity<String> response = restTemplate.exchange(url,
 * HttpMethod.GET, httpEntity, String.class); ObjectMapper mapper = new
 * ObjectMapper(); List<CountryLaguagesDto> namelanguagesList = Arrays
 * .asList(mapper.readValue(response.getBody(), CountryLaguagesDto[].class));
 * return namelanguagesList; }
 * 
 * @Override public List<CountryNameDto> getCountryNames() throws
 * JsonMappingException, JsonProcessingException { HttpEntity<Void> httpEntity =
 * new HttpEntity<>(gethttpHeaders()); String url = new
 * StringBuilder(baseUrl).append(GetAll).append("?fields=name").toString();
 * System.out.println(url); ResponseEntity<String> response =
 * restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
 * ObjectMapper mapper = new ObjectMapper(); List<CountryNameDto> nameList =
 * Arrays.asList(mapper.readValue(response.getBody(), CountryNameDto[].class));
 * nameList.sort(Comparator.comparing(CountryNameDto::getCommonName)); return
 * nameList; }
 * 
 * private HttpHeaders gethttpHeaders() { HttpHeaders headers = new
 * HttpHeaders(); headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
 * headers.setContentType(MediaType.APPLICATION_JSON); return headers; }
 * 
 * @Override public List getCountryNamesByField(String field, String sort)
 * throws JsonMappingException, JsonProcessingException { List responseList =
 * null; if (field.equals("population")) { responseList =
 * getCountryNamesByPopulation(sort); } else if (field.equals("area")) {
 * responseList = getCountryNamesByArea(sort); } else if
 * (field.equals("languages")) { responseList = getCountryNamesByLanguages(); }
 * else { throw new NoSuchFieldFoundException("Incorrect Field."); } return
 * responseList; }
 * 
 * public Page<CountryNameDto> getCountryNames(int page, int size) throws
 * JsonMappingException, JsonProcessingException {
 * 
 * HttpEntity<Void> httpEntity = new HttpEntity<>(gethttpHeaders()); String url
 * = new
 * StringBuilder(baseUrl).append(GetAll).append("?fields=name").toString();
 * System.out.println(url); ResponseEntity<String> response =
 * restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
 * ObjectMapper mapper = new ObjectMapper(); List<CountryNameDto> nameList =
 * Arrays .asList(mapper.readValue(response.getBody(), CountryNameDto[].class));
 * 
 * 
 * 
 * 
 * 
 * Pageable pageRequest = PageRequest.of(page, size); int start = (int)
 * pageRequest.getOffset(); int end = Math.min((start +
 * pageRequest.getPageSize()), nameList.size());
 * 
 * List<CountryNameDto> pageContent = nameList.subList(start, end); return new
 * PageImpl(pageContent, pageRequest,
 * nameList.size());//<CountryNameDto>(pageContent, pageRequest,
 * nameList.size());
 * 
 * }
 * 
 * @Override public List<Map<String, Object>> getAllCountriesDetails() {
 * 
 * HttpEntity<Void> httpEntity = new HttpEntity<>(gethttpHeaders()); String url
 * = new StringBuilder(baseUrl).append(GetAll).toString(); ResponseEntity<List>
 * response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
 * List.class); ; return response.getBody(); }
 * 
 * @Override public List<Country> getCountryByName(String name) throws
 * JsonMappingException, JsonProcessingException { HttpEntity <Void> httpEntity
 * = new HttpEntity<>(gethttpHeaders()); String url = new
 * StringBuilder(baseUrl).append(CountryByName).append(name).toString();
 * ResponseEntity<String> response; try { response = restTemplate.exchange(url ,
 * HttpMethod.GET ,httpEntity,String.class); }catch(HttpClientErrorException
 * httpClientException) { throw new
 * CountryNotFoundException(httpClientException.getStatusCode().toString()
 * ,"No Such a Country Found. Please send correct country name."); }
 * ObjectMapper mapper = new ObjectMapper();
 * mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
 * return Arrays.asList(mapper.readValue(response.getBody(), Country[].class));
 * // return response.getBody(); }
 * 
 * @Override public List<Map<String, Object>> getCountryNamesByFields(String
 * field) { HttpEntity<Void> httpEntity = new HttpEntity<>(gethttpHeaders());
 * String url = new
 * StringBuilder(baseUrl).append(GetAll).append("?fields=name,").append(field).
 * toString(); ResponseEntity<List> response = restTemplate.exchange(url,
 * HttpMethod.GET, httpEntity, List.class); return response.getBody();
 * 
 * }
 * 
 * @Override public List<Map<String, Object>> getCountryNamesByFields(String
 * field, int size) { HttpEntity<Void> httpEntity = new
 * HttpEntity<>(gethttpHeaders()); String url = new
 * StringBuilder(baseUrl).append(GetAll).append("?fields=name,").append(field).
 * toString(); ResponseEntity<List> response = restTemplate.exchange(url,
 * HttpMethod.GET, httpEntity, List.class); response.getBody().subList(0, size);
 * return response.getBody().subList(0, size); }
 * 
 * @Override public List<Map<String, Object>> getCountryNamesByPopulation(int
 * page, int limit) { HttpEntity<Void> httpEntity = new
 * HttpEntity<>(gethttpHeaders()); String url = new
 * StringBuilder(baseUrl).append(GetAll).append("?fields=name,population").
 * toString(); ResponseEntity<List> response = restTemplate.exchange(url,
 * HttpMethod.GET, httpEntity, List.class); System.out.println(url); int offset
 * = limit * (page - 1); System.out.println("offset = " + offset); return
 * response.getBody().subList(offset, offset + limit); }
 * 
 * }
 */