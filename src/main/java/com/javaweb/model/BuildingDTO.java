package com.javaweb.model;

public class BuildingDTO {

	private String name;
	private Integer numberofbasement;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getNumberofbasement() {
		return numberofbasement;
	}

	public void setNumberofbasement(Integer number) {
		this.numberofbasement = number;
	}

}
