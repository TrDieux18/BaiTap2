package com.javaweb.repository.imp;


import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.enity.DistrictEntity;
import com.javaweb.utils.ConnectionJdbcUtils;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
	
	@Override
	public DistrictEntity findNameById(Long id) {
		StringBuilder sql = new StringBuilder(" SELECT * FROM district d WHERE d.id = " + id + ";");
		DistrictEntity districtEntity = new DistrictEntity();
		try (Connection conn = ConnectionJdbcUtils.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString())) {

			System.out.println("Connected to database successfully...");
			
			
			while (rs.next()) {
				
				districtEntity.setName(rs.getString("name"));
				
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected to database failed...");
		}
		return districtEntity;
	}
}
