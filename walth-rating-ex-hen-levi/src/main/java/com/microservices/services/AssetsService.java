package com.microservices.services;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.microservices.dao.AssetsDao;
import com.microservices.model.Person;
import com.microservices.model.AssetPerCity;

@Component
@Service
public class AssetsService {
	private static Logger LOG = org.slf4j.LoggerFactory.getLogger(AssetsService.class);

	@Autowired
	AssetsDao assetsdao;
	@Autowired
	PersonService personservice;

	// get threshold
	public int getThreshold() {
		try {
			int threshold = 1000000000;
			return threshold;
		} catch (Exception e) {
			LOG.error("Error in service: AssetsService in  getThreshold function, exception:" + e);
			return -1;
		}
	}

	// the service will call the central bank to get asset evaluation per city -I
	// create small table with some cities and assets
	public int getAssetsByCity(String city) {
		try {
			int assetEvaluation = 0;
			List<AssetPerCity> AllAssets = assetsdao.findAll();

			for (AssetPerCity i : AllAssets)
				if (city.equals(i.getCity())) {
					assetEvaluation = i.getAssetEvaluation();
				}
			return assetEvaluation;
		} catch (Exception e) {
			LOG.error("Error in service: AssetsService in getAssetsByCity function, with city: " + city
					+ ", exception: " + e);
			return -1;
		}
	}

	public Long calculateFortune(Person person) {
		try {
			Long fortune;
			int id, threshold, assetEvaluation, numberOfAssets;
			id = person.getId();
			String city = person.getCity();
			Long cash = person.getCash();
			numberOfAssets = person.getNumberOfAssets();
			assetEvaluation = getAssetsByCity(city); // evaluate response
			fortune = cash + numberOfAssets * assetEvaluation; // calculate forture
			threshold = getThreshold();

			return fortune;
		} catch (Exception e) {
			LOG.error("Error in service: AssetsService in  calculateFortune function when get Person: " + person
					+ ", exception: " + e);
			return (long) -1;
		}

	}

}
