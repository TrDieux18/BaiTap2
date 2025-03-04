package com.javaweb.repository;


import java.util.List;
import java.util.Map;

import com.javaweb.repository.enity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll(Map<String, Object> pagrams, List<String> typeCode);
	void DeleteById(Long id);
}
