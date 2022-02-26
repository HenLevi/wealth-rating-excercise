package com.microservices.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name="person")
public class Person {
	@Id
    @GeneratedValue	
	int id;
	String firstName;
	String lastName;
	String city;
	Long cash;
	int numberOfAssets;	
}
