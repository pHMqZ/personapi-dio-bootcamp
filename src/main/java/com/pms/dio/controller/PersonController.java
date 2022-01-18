package com.pms.dio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pms.dio.dto.MessageResponseDTO;
import com.pms.dio.model.Person;
import com.pms.dio.service.PersonService;

@RestController
@RequestMapping("api/v1/people")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@RequestBody Person person) {
		return personService.create(person);
	}

}
