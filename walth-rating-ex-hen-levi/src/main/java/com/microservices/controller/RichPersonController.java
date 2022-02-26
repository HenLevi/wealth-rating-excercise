package com.microservices.controller;

import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.dao.PersonDao;
import com.microservices.dao.RichPersonDao;
import com.microservices.exception.E001PersonDoesntExistsException;
import com.microservices.model.RichPerson;
import com.microservices.services.PersonService;
import com.microservices.services.RichPersonService;

@RestController

public class RichPersonController {

	private static Logger LOG = org.slf4j.LoggerFactory.getLogger(RichPersonController.class);

	@Autowired
	RichPersonService richpersonservice;
	@Autowired
	PersonDao persondao;
	@Autowired
	PersonService personservice;
	@Autowired
	RichPersonDao richpersondao;

	// this is not required method
	@GetMapping("/getAllRichPersons")
	public ResponseEntity<List<RichPerson>> getAllRichPerson() {
			//throws Throwable {

			List<RichPerson> allRichPersons = richpersonservice.getAllRichPerson();

			if (allRichPersons==null) {
			LOG.error("Error in controller:RichPersonController in getAllRichPerson funcion, list of all rich persons");
	//		throw new E001PersonDoesntExistsException("person not exist in person lists");
			}
			return new ResponseEntity<List<RichPerson>>(allRichPersons, HttpStatus.FOUND);
	}

	@GetMapping("/getRichById/{id}")
	public ResponseEntity<RichPerson> getRichPersonById(@PathVariable(name = "id") int id) throws Throwable {

			RichPerson richperson = richpersonservice.getRichPersonById(id);

			if (richperson==null) {
			LOG.error("Error in controller:RichPersonController in getRichPersonById function with id: " + id);

			}
			return new ResponseEntity<RichPerson>(richperson, HttpStatus.FOUND);
	}

}
