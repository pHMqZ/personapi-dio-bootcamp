package com.pms.dio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.dio.dto.request.PersonDTO;
import com.pms.dio.dto.response.MessageResponseDTO;
import com.pms.dio.exception.PersonNotFoundException;
import com.pms.dio.mapper.PersonMapper;
import com.pms.dio.model.Person;
import com.pms.dio.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;

	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public MessageResponseDTO create(PersonDTO personDTO) {
		Person person = personMapper.toModel(personDTO);
		Person savedPerson = personRepository.save(person);
		return createResponse(savedPerson.getId(), "Created person with ID ");
	}

	public List<PersonDTO> listAll() {
		List<Person> people = personRepository.findAll();

		return people.stream().map(personMapper::toDTO).collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);
		return personMapper.toDTO(person);
	}
	
	
	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException{
		verifyIfExists(id);
		
		Person personUpdate = personMapper.toModel(personDTO);
		Person updatedPerson = personRepository.save(personUpdate);
		return createResponse(updatedPerson.getId(), "Updated person with ID ");
	}

	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		personRepository.deleteById(id);
	}
	
	private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
	
	private MessageResponseDTO createResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
