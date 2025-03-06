package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.enity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuilidingDTOConverter;

@Service
public class BuildingServiceImpl implements BuildingService{
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuilidingDTOConverter builidingDTOConverter;
	
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode){
			
		// TODO Auto-generated method stub
		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO buildingDTO = builidingDTOConverter.toBuildingDTO(item);
			result.add(buildingDTO);
		}
		return result;
	}
}
