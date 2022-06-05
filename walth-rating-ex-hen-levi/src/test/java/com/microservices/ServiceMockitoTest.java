package com.microservices;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.microservices.controller.AssetsController;
import com.microservices.controller.PersonController;
import com.microservices.dao.AssetsDao;
import com.microservices.dao.PersonDao;
import com.microservices.dao.RichPersonDao;
import com.microservices.model.Person;
import com.microservices.model.RichPerson;
import com.microservices.services.AssetsService;
import com.microservices.services.PersonService;
import com.microservices.services.RichPersonService;
import com.microservices.*;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ServiceMockitoTest.class })
class ServiceMockitoTest {

	@Mock PersonDao persondao;
	@InjectMocks PersonService personservice;
	
	@Mock RichPersonDao richpersondao;
	@InjectMocks RichPersonService richpersonservice;
	
	@Mock AssetsDao assetsdao;
	@InjectMocks AssetsService assetsservice;
	
	
	/*PersonService*/
	
	@Test
	public void test_getAllPersons() {

		List<Person> allPersons = new ArrayList<Person>();
		allPersons.add(new Person(999999999, "Hen", "Levi", "Rehovot", (long) 777777777, 4));
		allPersons.add(new Person(888888888, "Hana", "Mor", "Tel-Aviv", (long) 88888888, 9));

		when(persondao.findAll()).thenReturn(allPersons);// Mocking

		assertEquals(2, personservice.getAllPerson().size());

	}

	@Test
	public void test_getPersonById() {

		int personId = 555555555;
		List<Person> allPersons = new ArrayList<Person>();
		allPersons.add(new Person(999999999, "Hen", "Levi", "Rehovot", (long) 777777777, 4));
		allPersons.add(new Person(555555555, "Hana", "Mor", "Tel-Aviv", (long) 88888888, 9));
		when(persondao.findAll()).thenReturn(allPersons);// Mocking
		assertEquals(personId, personservice.getPersonById(personId).getId());
	}

	@Test
	public void test_getPersonsByCity() {

		String cityName = "Ashdod";
		List<Person> personsFromCity = new ArrayList<Person>();
		personsFromCity.add(new Person(999999999, "Hen", "Levi", "Ashdod", (long) 777777777, 4));
		personsFromCity.add(new Person(555555555, "Hana", "Mor", "tt", (long) 88888888, 9));
		personsFromCity.add(new Person(888888888, "roni", "oren", "Ashdod", (long) 99999333, 90));
		when(persondao.findAll()).thenReturn(personsFromCity);// Mocking
		assertEquals(2, personservice.getPersonsByCity(cityName).size());

	}

	/*richPersonService*/
	
	@Test
	public void test_getAllRichPerson() {

		List<RichPerson> allRichPersons = new ArrayList<RichPerson>();
		allRichPersons.add(new RichPerson(999999999, "Hen", "Levi", 88888888));
		allRichPersons.add(new RichPerson(888888888, "Hana", "Mor", 98510000));
		when(richpersondao.findAll()).thenReturn(allRichPersons);
		assertEquals(2, richpersonservice.getAllRichPerson().size());

	}

	@Test
	public void test_getRichPersonById() {

		int richpersonId = 999999999;
		List<RichPerson> richPersons = new ArrayList<RichPerson>();
		richPersons.add(new RichPerson(999999999, "Hen", "Levi", 88888888));
		richPersons.add(new RichPerson(888888888, "Hana", "Mor", 98510000));
		when(richpersondao.findAll()).thenReturn(richPersons);// Mocking
		assertEquals(richpersonId, richpersonservice.getRichPersonById(richpersonId).getId());
	}
	

	
	/*assetsService*/
	


}
