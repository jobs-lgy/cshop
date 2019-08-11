package com.javachen.cshop.common.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
	
	private String province;
	
	private String city;
	
	private String area;
	
	private String street;
	
	private String zipcode;
}
