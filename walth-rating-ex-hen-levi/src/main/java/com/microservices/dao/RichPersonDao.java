package com.microservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservices.model.RichPerson;

public interface RichPersonDao extends JpaRepository<RichPerson,Integer> {

}
