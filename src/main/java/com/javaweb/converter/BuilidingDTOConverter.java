package com.javaweb.converter;

//import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.enity.BuildingEntity;
import com.javaweb.repository.enity.DistrictEntity;
import com.javaweb.repository.enity.RentAreaEnity;

@Component
public class BuilidingDTOConverter {
	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO buildingDTO = modelMapper.map(item, BuildingDTO.class);

		DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictid());
		List<RentAreaEnity> rentAreaEnities = rentAreaRepository.getValueByBuildingId(item.getId());
		String listRentArea = rentAreaEnities.stream().map(it -> it.getValue().toString())
				.collect(Collectors.joining(","));
		buildingDTO.setRentArea(listRentArea);
		buildingDTO.setAddress(item.getStreet() + "," + item.getWard() + "," + districtEntity.getName());
		
		
//		buildingDTO.setId(item.getId());
//		buildingDTO.setName(item.getName());
//		buildingDTO.setDistrictid(item.getDistrictid());
//		buildingDTO.setFloorArea(item.getFloorArea());
//		buildingDTO.setManagerName(item.getManagerName());
//		buildingDTO.setManagerPhoneNumber(item.getManagerPhoneNumber());
//		buildingDTO.setRentPrice(item.getRentPrice());
		
		
		return buildingDTO;
	}
}
