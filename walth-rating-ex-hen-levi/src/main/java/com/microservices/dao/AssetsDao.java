package com.microservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservices.model.AssetPerCity;
@Repository
public interface AssetsDao extends JpaRepository<AssetPerCity,Integer> {

}
