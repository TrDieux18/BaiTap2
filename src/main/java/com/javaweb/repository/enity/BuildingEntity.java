package com.javaweb.repository.enity;

public class BuildingEntity {
	private Long id;
	private String name;
	private String ward;
	private String street;
	private Long districtid;
	private String managerName;
	private String managerPhoneNumber;
	private Long floorArea;
//	private String emptyArea;
	private Long rentPrice;

	private String rentArea;
	private String serviceFee;
//	private Long brokeageFee;

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getDistrictid() {
		return districtid;
	}

	public void setDistrictid(Long districtid) {
		this.districtid = districtid;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}

	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}

	public Long getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}

//	public String getEmptyArea() {
//		return emptyArea;
//	}
//	public void setEmptyArea(String emptyArea) {
//		this.emptyArea = emptyArea;
//	}
	public Long getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getRentArea() {
		return rentArea;
	}

	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

//	public Long getBrokeageFee() {
//		return brokeageFee;
//	}
//
//	public void setBrokeageFee(Long brokeageFee) {
//		this.brokeageFee = brokeageFee;
//	}

}
