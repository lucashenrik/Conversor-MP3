package com.lucas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lucas.configurations.RapidApiProperties;

@Service
public class ApiService {

	@Autowired
	private RapidApiProperties rapidApiProperties;
	
	private RestTemplate restTemplate;

	@Autowired
	public ApiService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public String callApi(String videoUrl) {
		
		String apiUrl = rapidApiProperties.getApiUrl() + videoUrl;
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("X-RapidApi-Key", rapidApiProperties.getKey());
		headers.set("X-RapidApi-Host", rapidApiProperties.getHost());
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
		
		return response.getBody();
	}
	
	
}
