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
import com.javaweb.converter.BuilidingDTOConverter;

@Service
public class BuildingServiceImpl implements BuildingService{
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuilidingDTOConverter builidingDTOConverter;
	
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> pagrams, List<String> typeCode){
		// TODO Auto-generated method stub
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(pagrams, typeCode);
		
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO buildingDTO = builidingDTOConverter.toBuildingDTO(item);
			result.add(buildingDTO);
		}
		return result;
	}
}
