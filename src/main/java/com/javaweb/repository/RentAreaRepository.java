package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.enity.RentAreaEnity;

public interface RentAreaRepository {
	List<RentAreaEnity> getValueByBuildingId (Long id);
}
