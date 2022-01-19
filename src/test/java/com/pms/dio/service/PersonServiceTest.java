package com.pms.dio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pms.dio.dto.request.PersonDTO;
import com.pms.dio.dto.response.MessageResponseDTO;
import com.pms.dio.model.Person;
import com.pms.dio.repository.PersonRepository;
import com.pms.dio.utils.PersonUtils;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private PersonService personService;

	@Test
	void testGivenPersonDTOThenReturnSavedMessage() {
		PersonDTO personDTO = PersonUtils.createFakeDTO();
		Person expectedSavedPerson = PersonUtils.createFakeEntity();

		Mockito.when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

		MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
		MessageResponseDTO successMessage = personService.create(personDTO);
	
		assertEquals(expectedSuccessMessage, successMessage);
	}

	private MessageResponseDTO createExpectedMessageResponse(Long id) {
		return MessageResponseDTO.builder().message("Created person with ID " + id).build();
	}
}
