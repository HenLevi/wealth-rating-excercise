package com.microservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.services.AssetsService;

@RestController
@RequestMapping(value = "/central-bank")
public class AssetsController {

	private static Logger LOG = LoggerFactory.getLogger(AssetsController.class);

	@Autowired
	AssetsService assetsService;

	@GetMapping("/regional-info/evaluate/city")
	public ResponseEntity<Integer> getAssetsByCity(@RequestParam(value = "city") String city) {
		try {
			int assetsByCity = assetsService.getAssetsByCity(city);
			return new ResponseEntity<Integer>(assetsByCity, HttpStatus.FOUND);
		} catch (Exception e) {
			LOG.error("Error in controller:AssetsController in getAssetsByCity function, exception:" + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/wealth-threshold")
	public ResponseEntity<Integer> getThreshold() {
		try {
			int threshold = assetsService.getThreshold();
			return new ResponseEntity<Integer>(threshold, HttpStatus.FOUND);
		} catch (Exception e) {
			LOG.error("Error in controller:AssetsController in getThreshold function, exception:" + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
