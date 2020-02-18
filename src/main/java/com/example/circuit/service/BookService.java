package com.example.circuit.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
public class BookService {
	
	private final RestTemplate restTemplate;

	public BookService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@HystrixCommand(fallbackMethod="reliable")
	public String readingList() {
		URI uri = URI.create("http://localhost:8090/recommended");
		return this.restTemplate.getForObject(uri, String.class);
	}
	
	public String reliable() {
		 return "Cloud Native Java (O'Reilly)";
	  }

}
