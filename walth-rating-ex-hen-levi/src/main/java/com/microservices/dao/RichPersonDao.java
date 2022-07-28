package com.microservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservices.model.RichPerson;
@Repository
public interface RichPersonDao extends JpaRepository<RichPerson,Integer> {

}
