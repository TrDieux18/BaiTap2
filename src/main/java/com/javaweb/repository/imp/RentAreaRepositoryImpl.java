package com.javaweb.repository.imp;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.enity.RentAreaEnity;
import com.javaweb.utils.ConnectionJdbcUtils;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

	@Override
	public List<RentAreaEnity> getValueByBuildingId(Long id) {
		String sql = " SELECT * FROM rentarea ra WHERE ra.buildingid = " + id;
		List<RentAreaEnity> rentAreaList = new ArrayList<>();

		try (Connection conn = ConnectionJdbcUtils.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString())) {

			System.out.println("Connected to database successfully...");

			while (rs.next()) {
				RentAreaEnity rentAreaEnity = new RentAreaEnity();
				rentAreaEnity.setValue(rs.getString("value"));
				rentAreaList.add(rentAreaEnity);
				}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected to database failed...");
		}
		return rentAreaList;
	}
}
