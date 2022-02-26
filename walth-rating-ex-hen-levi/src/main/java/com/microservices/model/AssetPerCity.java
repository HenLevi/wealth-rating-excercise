package com.microservices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="assets")
public class AssetPerCity {
	@Id
	@Column(name="id_asset")	
	int idAsset;
	
	@Column(name="city")
	String city;
	
	@Column(name="asset_evaluation")
	int assetEvaluation;
		
}
