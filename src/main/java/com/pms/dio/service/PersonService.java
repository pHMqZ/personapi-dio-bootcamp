package com.pms.dio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.dio.dto.MessageResponseDTO;
import com.pms.dio.model.Person;
import com.pms.dio.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public MessageResponseDTO create(Person person) {
		Person savedPerson = personRepository.save(person);
		return MessageResponseDTO
				.builder()
				.message("Created person with Id " + savedPerson.getId())
				.build();
	}
	
}
