package com.comakeit.whms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RestUrl {
	
	@Value("${service.restcall.url}")
	private String rest;
	
	public String getrestURL() {
		return rest;
	}
}
