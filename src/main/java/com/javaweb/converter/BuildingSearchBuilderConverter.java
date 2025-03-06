package com.javaweb.converter;

//import java.lang.module.ModuleDescriptor.Builder;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.utils.MapUtils;

import com.javaweb.builder.BuildingSearchBuilder;
//import com.javaweb.builder.BuildingSearchBuilder.Builder;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
				.setName(MapUtils.getObject(params, "name", String.class))
				.setStreet(MapUtils.getObject(params, "street", String.class))
				.setWard(MapUtils.getObject(params, "ward", String.class))
				.setDistrictid(MapUtils.getObject(params, "districtid", Long.class))
				.setNumberofbasement(MapUtils.getObject(params, "numberofbasement", Integer.class))
				.setFloorarea(MapUtils.getObject(params, "floorarea", Long.class))
				.setRentpricefrom(MapUtils.getObject(params, "rentpricefrom", Long.class))
				.setRentpriceto(MapUtils.getObject(params, "rentpriceto", Long.class))
				.setRentareafrom(MapUtils.getObject(params, "rentareafrom", Long.class))
				.setRentareato(MapUtils.getObject(params, "rentareato", Long.class))
				.setManagername(MapUtils.getObject(params, "managername", String.class))
				.setManagerphonenumber(MapUtils.getObject(params, "managerphonenumber", String.class))
				.setStaffid(MapUtils.getObject(params, "staffid", Long.class)).setTypeCode(typeCode).build();

		return buildingSearchBuilder;
	}
}
