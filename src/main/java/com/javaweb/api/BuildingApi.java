package com.javaweb.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;

@RestController

public class BuildingApi {

	@Autowired
	private BuildingService buildingService;

	@GetMapping("/api/building/")
	public List<BuildingDTO> getBuildings(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "district", required = false) String district,
			@RequestParam(name = "typeCode", required = false) List<String> typeCode){
		List<BuildingDTO> result = buildingService.findAll(name, district);
		return result;
	}
 // delete
	@DeleteMapping("/api/building/{id}/{name}")
	public void deleteBuilding(@PathVariable Integer id, @PathVariable String name,
			@RequestParam(value = "address", required = false) String address) {
		System.out.println("Da xoa toa nha id " + id + " " + name );
		
	}
}
