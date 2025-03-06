package com.javaweb.repository;


import java.util.List;
import java.util.Map;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.enity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
	void DeleteById(Long id);
}
