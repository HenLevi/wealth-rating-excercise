package com.microservices.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.exception.E001PersonDoesntExistsException;
import com.microservices.model.Person;
import com.microservices.services.PersonService;

@RestController
public class PersonController {

	private static Logger LOG = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonService personservice;

	@GetMapping("/getAllPersons")
	public ResponseEntity<List<Person>> getAllPerson() throws Throwable {

		List<Person> allPersons = personservice.getAllPerson();
		if (allPersons == null) {
			LOG.error("Error in controller: PersonController in getAllPerson function, list of all persons");
			throw new E001PersonDoesntExistsException("person not exist in person lists");
		}
		return new ResponseEntity<List<Person>>(allPersons, HttpStatus.FOUND);
	}

	@GetMapping("/getPersonById/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable(name = "id") int id) throws Throwable {

		Person person = personservice.getPersonById(id);
		if (person == null) {
			LOG.error("Error in controller:PersonController in getPersonById function with id:" + id);
			throw new E001PersonDoesntExistsException("person not exist in person lists");
		}

		return new ResponseEntity<Person>(person, HttpStatus.FOUND);

	}

	@GetMapping("/getPerson/city")
	public ResponseEntity<List<Person>> getPersonsByCity(@RequestParam(value = "city") String city) throws Throwable {
		
		List<Person> personsByCity = personservice.getPersonsByCity(city);
		if (personsByCity == null) {
			LOG.error("Error in controller:PersonController in getPersonsByCity function with city: " + city);
			throw new E001PersonDoesntExistsException("person not exist in person lists");
		}
		return new ResponseEntity<List<Person>>(personsByCity,HttpStatus.FOUND);

	}

}
