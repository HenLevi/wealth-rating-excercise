package com.microservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservices.model.Person;
@Repository
public interface PersonDao extends JpaRepository<Person,Integer> {

}

