package com.javaweb.repository.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.enity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	static final String DB_URL = "jdbc:mysql://localhost:3306/estateadvance";
	static final String USER = "root";
	static final String PASS = "trandieu2210";

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> pagrams) {
		List<BuildingEntity> buildings = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM building b WHERE 1 = 1 ");
		
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString())) {

			System.out.println("Connected to database successfully...");

			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setWard(rs.getString("ward"));
				building.setStreet(rs.getString("street"));
				building.setNumberofbasement(rs.getInt("Numberofbasement"));

				buildings.add(building);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected to database failed...");
		}

		return buildings;
	}

	@Override
	public void DeleteById(Long id) {
		// TODO Auto-generated method stub

	}
}
