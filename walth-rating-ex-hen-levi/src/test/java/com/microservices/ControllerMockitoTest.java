package com.microservices;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
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
import com.microservices.controller.RichPersonController;
import com.microservices.model.Person;
import com.microservices.model.RichPerson;
import com.microservices.services.AssetsService;
import com.microservices.services.PersonService;
import com.microservices.services.RichPersonService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ControllerMockitoTest.class })

class ControllerMockitoTest {

	@Mock PersonService personservice;
	@InjectMocks PersonController personcontroller;
	
	@Mock RichPersonService richpersonservice;
	@InjectMocks RichPersonController richpersoncontroller;
	
	@Mock AssetsService assetsservice;
	@InjectMocks AssetsController assetscontroller;
	
	
	List<Person> allPersons;
	Person person;
	
	List<RichPerson> allRichPersons;
	Person richperson;
	
	/*Person controller*/
	@Test
	@Order(1)
	public void test_getAllPersons() throws Throwable {
		List<Person> allPersons = new ArrayList<Person>();
		allPersons.add(new Person(999999999, "Hen", "Levi", "Rehovot", (long) 777777777, 4));
		allPersons.add(new Person(888888888, "Hana", "Mor", "Tel-Aviv", (long) 88888888, 9));

		when(personservice.getAllPerson()).thenReturn(allPersons);
		ResponseEntity<List<Person>> res = personcontroller.getAllPerson();
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
	}

	@Test
	@Order(2)
	public void test_getPersonById() throws Throwable {
		int personId = 555555555;
		person = new Person(555555555, "Hen", "Levi", "Rehovot", (long) 777777777, 4);
		when(personservice.getPersonById(personId)).thenReturn(person);
		ResponseEntity<Person> res = personcontroller.getPersonById(personId);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(personId, res.getBody().getId());

	}

	@Test
	@Order(3)
	public void test_getCountryByCity() throws Throwable {
		person = new Person(555555555, "Hen", "Levi", "Rehovot", (long) 777777777, 4);
		person = new Person(666666666, "Hana", "Li", "Aco", (long) 88888888, 4);
		List allPersonsByCity = new ArrayList<Person>();
		String cityName = "Rehovot";
		when(personservice.getAllPerson()).thenReturn(allPersons);
		ResponseEntity<List<Person>> res = personcontroller.getPersonsByCity(cityName);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
	}
	
	/*richPerson controller*/
	@Test
	@Order(4)
	public void test_getAllRichPersons() throws Throwable {
		List<RichPerson> allRichPersons = new ArrayList<RichPerson>();
		allRichPersons.add(new RichPerson(999999999,"Hen","Levi",88888888));
		allRichPersons.add(new RichPerson(888888888,"Hana","Mor",98510000));

		when(richpersonservice.getAllRichPerson()).thenReturn(allRichPersons);
		ResponseEntity<List<RichPerson>> res = richpersoncontroller.getAllRichPerson();
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
	}

	@Test
	@Order(5)
	public void test_getRichPersonById() throws Throwable {
		int richpersonId = 555555555;
		RichPerson richperson = new RichPerson(555555555,"Hen","Levi",7000000);
		when(richpersonservice.getRichPersonById(richpersonId)).thenReturn(richperson);
		ResponseEntity<RichPerson> res = richpersoncontroller.getRichPersonById(richpersonId);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(richpersonId, res.getBody().getId());

	}
	
	/*assets controller*/
	@Test
	@Order(6)
	public void test_getAssetsByCity() {
		String city = "Rehovot";
	    int assets=9000;
		when(assetsservice.getAssetsByCity(city)).thenReturn(assets);
		ResponseEntity <Integer> res = assetscontroller.getAssetsByCity(city);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(9000, Integer.valueOf(assets));
	}
	
	
	@Test
	@Order(7)
	public void test_getThreshold() {
	
		int threshold=1000000000;
		when(assetsservice.getThreshold()).thenReturn(threshold);
		ResponseEntity <Integer> res = assetscontroller.getThreshold();
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		res.getBody();
		assertEquals(1000000000, Integer.valueOf(threshold));
	}	
	
	
	
	
	

}
