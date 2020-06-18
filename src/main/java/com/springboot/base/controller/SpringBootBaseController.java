package com.springboot.base.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SpringBootBaseController {

	@RequestMapping(value = "/v1/springbootbase/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> geyPaymentToken(@PathVariable String id) throws Exception {
		log.info("Processing the request...................");
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("param", id);
		return paramMap;
	}

}
