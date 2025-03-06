package com.javaweb.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;

@RestController

public class BuildingApi {

	@Autowired
	private BuildingService buildingService;

	@GetMapping("/api/building/")
	public List<BuildingDTO> getBuildings(@RequestParam Map<String, Object> params,
			@RequestParam(name = "typeCode", required = false) List<String> typeCode){
		List<BuildingDTO> result = buildingService.findAll(params, typeCode);
		return result;
	}


}
