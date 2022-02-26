package com.microservices.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.microservices.dao.PersonDao;
import com.microservices.model.Person;

@Component
@Service

public class PersonService {
	private static Logger LOG = org.slf4j.LoggerFactory.getLogger(PersonService.class);

	@Autowired
	PersonDao persondao;

	public List<Person> getAllPerson() {
		try {
			List<Person> allPersons = persondao.findAll();
			return allPersons;
		} catch (Exception e) {
			LOG.error("Error in service: PersonService in getAllPerson function, list of all person, exception:" + e);
			return null;
		}
	}

	public Person getPersonById(int id) {
		try {
			List<Person> allPersons = persondao.findAll();
			Person person = null;
			person = allPersons.stream()
					.filter(personFilter -> String.valueOf(id).equals(String.valueOf(personFilter.getId()))).findFirst()
					.orElse(null);

			return person;
		} catch (Exception e) {
			LOG.error("Error in service: PersonService in getPersonById function with id: " + id + ", exception:" + e);
			return null;
		}

	}

	// all persons from chosen city
	public List<Person> getPersonsByCity(String city) {
		try {
			List<Person> AllPerson = persondao.findAll();
			List<Person> personsFromCity = new ArrayList<Person>();
			for (Person i : AllPerson)
				if (city.equals(i.getCity()))
					personsFromCity.add(i);
			return personsFromCity;
		} catch (Exception e) {
			LOG.error("error in service:PersonService in getPersonsByCity function with city: " + city + ", exception:"
					+ e);
			return null;
		}

	}

}
