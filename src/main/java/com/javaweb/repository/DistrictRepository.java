package com.javaweb.repository;

//import java.lang.System.Logger;

import com.javaweb.repository.enity.DistrictEntity;

public interface DistrictRepository {
	DistrictEntity findNameById(Long id);
}
