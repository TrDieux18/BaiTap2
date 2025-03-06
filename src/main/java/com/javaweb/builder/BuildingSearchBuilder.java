package com.javaweb.builder;

import java.util.List;
//import java.beans.JavaBean;
import java.util.ArrayList;

//@JavaBean
//@SuppressWarnings("rawtypes")
//@SuppressWarnings("unchecked")
public class BuildingSearchBuilder {
	private String name;
	private Long floorarea;
	private String ward;
	private String street;
	private Long districtid;
	private Integer numberofbasement;

	
	private List<String> typeCode = new ArrayList();
	private String managername;
	private String managerphonenumber;
	private Long rentpricefrom;
	private Long rentpriceto;
	private Long rentareafrom;
	private Long rentareato;
	private Long staffid;
	
	public BuildingSearchBuilder(Builder builder) {
		// TODO Auto-generated constructor stub	(Builder builder) {
		this.name = builder.name;
		this.street = builder.street;
		this.districtid = builder.districtid;
		this.numberofbasement = builder.numberofbasement;
		this.floorarea = builder.floorarea;
		this.rentpricefrom = builder.rentpricefrom;
		this.rentpriceto = builder.rentpriceto;
		this.rentareafrom = builder.rentareafrom;
		this.rentareato = builder.rentareato;
		this.managername = builder.managername;
		this.managerphonenumber = builder.managerphonenumber;
		this.staffid = builder.staffid;
		this.typeCode = builder.typeCode;
		this.ward = builder.ward;
	}

	public String getName() {
		return name;
	}

	public Long getFloorarea() {
		return floorarea;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public Long getDistrictid() {
		return districtid;
	}

	public Integer getNumberofbasement() {
		return numberofbasement;
	}

	public List<String> getTypeCode() {
		return typeCode;
	}

	public String getManagername() {
		return managername;
	}

	public String getManagerphonenumber() {
		return managerphonenumber;
	}

	public Long getRentpricefrom() {
		return rentpricefrom;
	}

	public Long getRentpriceto() {
		return rentpriceto;
	}

	public Long getRentareafrom() {
		return rentareafrom;
	}

	public Long getRentareato() {
		return rentareato;
	}

	public Long getStaffid() {
		return staffid;
	}

	public static class Builder
	{
		private String name;
		private Long floorarea;
		private String ward;
		private String street;
		private Long districtid;
		private Integer numberofbasement;
		private List<String> typeCode = new ArrayList();
		private String managername;
		private String managerphonenumber;
		private Long rentpricefrom;
		private Long rentpriceto;
		private Long rentareafrom;
		private Long rentareato;
		private Long staffid;

		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setFloorarea(Long floorarea) {
			this.floorarea = floorarea;
			return this;
		}

		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setDistrictid(Long districtid) {
			this.districtid = districtid;
			return this;
		}

		public Builder setNumberofbasement(Integer numberofbasement) {
			this.numberofbasement = numberofbasement;
			return this;
		}

		

		public Builder setRentpricefrom(Long rentpricefrom) {
			this.rentpricefrom = rentpricefrom;
			return this;
		}

		public Builder setRentpriceto(Long rentpriceto) {
			this.rentpriceto = rentpriceto;
			return this;
		}

		public Builder setRentareafrom(Long rentareafrom) {
			this.rentareafrom = rentareafrom;
			return this;
		}

		public Builder setRentareato(Long rentareato) {
			this.rentareato = rentareato;
			return this;
		}

		

		public Builder setManagername(String managername) {
			this.managername = managername;
			return this;
		}

		public Builder setManagerphonenumber(String managerphonenumber) {
			this.managerphonenumber = managerphonenumber;
			return this;
		}

		public Builder setStaffid(Long staffid) {
			this.staffid = staffid;
			return this;
		}

		public Builder setTypeCode(List<String> typeCode) {
			this.typeCode = typeCode;
			return this;
		}

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}

	}
}

	
