package com.sapient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@EnableCircuitBreaker
public class EmployeeController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping
	@RequestMapping(value = "/documents")
	@HystrixCommand(fallbackMethod="fallbackAPI")
	 public String getDocument() {
	  return restTemplate.getForObject("https://api.myjson.com/bins/o6d42", String.class);
	 }
	
	
	@GetMapping
	@RequestMapping(value = "/technicalDocument")
	@HystrixCommand(fallbackMethod="fallbackAPI")
	 public String technicalDocument() {
	  return restTemplate.getForObject("https://api.myjson123.com/bins/o6d42", String.class);
	 }
	
	
	 public String fallbackAPI() {
		  return "API Down Please try after some time";
	 }
}
