package com.lucas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucas.services.ApiService;

@RestController
@RequestMapping(value = "/download")
public class ApiController {

	@Autowired
	private ApiService service;
	
	@PostMapping
	public ModelAndView callApi(@RequestParam String videoUrl) {
		String jsonResponse = service.callApi(videoUrl);

		// Parse the JSON response to extract the download link
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode rootNode = objectMapper.readTree(jsonResponse);
			String downloadLink = rootNode.path("dlink").asText();

			 // Redirect the user to the download link
			return new ModelAndView("redirect:" + downloadLink);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("error");
		}
	}
}
