package com.microservices.services;

import java.util.List;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.microservices.dao.PersonDao;
import com.microservices.dao.RichPersonDao;
import com.microservices.exception.E001PersonDoesntExistsException;
import com.microservices.exception.E002DuplicatePersonIDException;
import com.microservices.model.Person;
import com.microservices.model.RichPerson;

@Component
@Service
@Transactional
public class RichPersonService {

	private static Logger LOG = org.slf4j.LoggerFactory.getLogger(RichPersonService.class);

	@Autowired
	AssetsService assetsService;
	@Autowired
	RichPersonDao richpersondao;
	@Autowired
	PersonDao persondao;
	@Autowired
	PersonService personservice;

	public List<RichPerson> getAllRichPerson() {
		try {
			List<RichPerson> allRichPersons = checkIfToAddNewRichPerson();
			return allRichPersons;
		} catch (Exception e) {
			LOG.error(
					"Error in service:RichPersonService in getAllRichPerson, list of all rich persons, exception:" + e);
			return null;
		}
	}

	public RichPerson getRichPersonById(int id) {
		try {
			List<RichPerson> allRichPersons = richpersondao.findAll();
			RichPerson richPerson = null;
			richPerson = allRichPersons.stream()
					.filter(richPersonFilter -> String.valueOf(id).equals(String.valueOf(richPersonFilter.getId())))
					.findFirst().orElse(null);
			return richPerson;
		} catch (Exception e) {
			LOG.error("Error in service:RichPersonService  getRichPersonById funcion with id: " + id + ", exception:"
					+ e);
			return null;
		}
	}

	public List<RichPerson> checkIfToAddNewRichPerson() {
		try {
			List<RichPerson> richpersonAll = richpersondao.findAll();
			List<Person> AllPersons = persondao.findAll();
			long fortune = 0;
			int threshold = 0;
			for (Person i : AllPersons) {
				fortune = assetsService.calculateFortune(i);
				threshold = assetsService.getThreshold();
				if (fortune > threshold) {
					// check if rich person already exist
					RichPerson rp = getRichPersonById(i.getId());
				
					if (rp != null) { //means he exist in rich person table
 						System.out.println(rp.getFirstName()+" "+rp.getLastName() +" is rich person already exists");
					}
					else {
					RichPerson r = new RichPerson();
					// insert to rich table
					r.setId(i.getId());
					r.setFirstName(i.getFirstName());
					r.setLastName(i.getLastName());
					r.setFortune(fortune);
					richpersondao.save(r);
					richpersonAll.add(r);
					}
				}
			}

			return richpersonAll;
		} catch (Exception e) {
			LOG.error("Error in service:RichPersonService in checkIfToAddNewRichPerson function, exception:" + e);
			return null;
		}

	}

}
