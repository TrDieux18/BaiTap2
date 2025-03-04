package com.javaweb.repository.imp;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.enity.BuildingEntity;
import com.javaweb.utils.ConnectionJdbcUtils;
import com.javaweb.utils.NumberUtils;
import com.javaweb.utils.StringUtils;

//import org.springframework.util.NumberUtils;
@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	public static void joinTable(Map<String, Object> pagrams, List<String> typeCode, StringBuilder sql) {
		String staffid = (String) (pagrams.get("staffid"));
		if (StringUtils.checkString(staffid)) {
			sql.append("\nINNER JOIN assignmentbuilding amb ON b.id = amb.buildingid ");
		}

		if (typeCode != null && typeCode.size() != 0) {
			sql.append("\nINNER JOIN buildingrenttype brt ON b.id = brt.buildingid ");
			sql.append("\nINNER JOIN renttype rt ON brt.renttypeid = rt.id");
		}
		String rentAreaTo = (String) pagrams.get("floorareaTo");
		String rentAreaFrom = (String) pagrams.get("floorareaFrom");
		if (StringUtils.checkString(rentAreaFrom) || StringUtils.checkString(rentAreaTo)) {
			sql.append("\nINNER JOIN rentarea ra ON b.id = ra.buildingid ");
		}

	}

	public static void queryNormal(Map<String, Object> pagrams, StringBuilder where) {
		for (Map.Entry<String, Object> it : pagrams.entrySet()) {
			if (!it.getKey().equals("staffid") && !it.getKey().equals("typeCode") && !it.getKey().startsWith("floor")
					&& !it.getKey().startsWith("rentprice")) {
				String value = it.getValue().toString();
				if (StringUtils.checkString(value)) {
					if (NumberUtils.isNumber(value) == true) {
						where.append("\nAND b." + it.getKey() + " = " + value);
					} else {
						where.append("\nAND b." + it.getKey() + " LIKE '%" + value + "%' ");
					}
				}
			}
		}
	}

	public static void querySpecial(Map<String, Object> pagrams, List<String> typeCode, StringBuilder where) {
		String staffid = (String) pagrams.get("staffid");
		if (StringUtils.checkString(staffid)) {
			where.append("\nAND amb.staffid = " + staffid);
		}

		String rentAreaTo = (String) pagrams.get("floorareaTo");
		String rentAreaFrom = (String) pagrams.get("floorareaFrom");
//		

		if (StringUtils.checkString(rentAreaFrom) || StringUtils.checkString(rentAreaTo)) {
			where.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE b.id = ra.buildingid ");
			if (StringUtils.checkString(rentAreaFrom)) {
				where.append("\nAND ra.value >= " + rentAreaFrom);
			}

			if (StringUtils.checkString(rentAreaTo)) {
				where.append("\nAND ra.value <= " + rentAreaTo);
			}
			where.append(") ");
		}

		String rentPriceTo = (String) pagrams.get("rentpriceTo");
		String rentPriceFrom = (String) pagrams.get("rentpriceFrom");
		if (StringUtils.checkString(rentPriceFrom)) {
			where.append("\nAND b.rentprice >= " + rentPriceFrom);
		}

		if (StringUtils.checkString(rentPriceFrom)) {
			where.append("\nAND b.rentprice <= " + rentPriceTo);
		}

		if (typeCode != null && typeCode.size() != 0) {
//			where.append("\nAND rt.code IN ('" + String.join(",", typeCode) + "') ");

			where.append("\nAND ( ");
			String sql = typeCode.stream().map(it -> "rt.code LIKE " + "'%" + it + "%' ")
					.collect(Collectors.joining(" OR "));
			where.append(sql + " ) ");
		}
	}

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> pagrams, List<String> typeCode) {

		StringBuilder sql = new StringBuilder(
				"SELECT b.id, b.name, b.street, b.ward, b.districtid, b.numberofbasement, b.floorarea, b.rentprice,\n"
						+ "b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee FROM building b ");
		joinTable(pagrams, typeCode, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1");
		queryNormal(pagrams, where);
		querySpecial(pagrams, typeCode, where);
		where.append("\nGROUP BY b.id;");
		sql.append(where);
		System.out.println(sql);
		List<BuildingEntity> buildings = new ArrayList<>();
		try (Connection conn = ConnectionJdbcUtils.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString())) {

			System.out.println("Connected to database successfully...");

			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				building.setWard(rs.getString("ward"));
				building.setStreet(rs.getString("street"));
				building.setDistrictid(rs.getLong("districtid"));
				building.setManagerName(rs.getString("managername"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				building.setFloorArea(rs.getLong("floorarea"));
				building.setRentPrice(rs.getLong("rentprice"));
				building.setServiceFee(rs.getString("servicefee"));
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
