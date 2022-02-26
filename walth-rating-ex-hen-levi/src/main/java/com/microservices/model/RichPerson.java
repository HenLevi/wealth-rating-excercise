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
@Table(name="rich_persons")
public class RichPerson {
	@Id
	@Column(name="id")	
	int id;
	
	@Column(name="first_name")
	String firstName;
	
	@Column(name="last_name")
	String lastName;

	@Column(name="fortune")
	long fortune;
	
	
	
	
}
