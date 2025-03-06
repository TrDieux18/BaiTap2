package com.javaweb.repository.imp;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.enity.BuildingEntity;
import com.javaweb.utils.ConnectionJdbcUtils;
import com.javaweb.utils.NumberUtils;
import com.javaweb.utils.StringUtils;

//import org.springframework.util.NumberUtils;
@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Long staffid = buildingSearchBuilder.getStaffid();
		if (staffid != null) {
			sql.append("\nINNER JOIN assignmentbuilding amb ON b.id = amb.buildingid ");
		}

		List<String> typeCode = buildingSearchBuilder.getTypeCode();

		if (typeCode != null && typeCode.size() != 0) {
			sql.append("\nINNER JOIN buildingrenttype brt ON b.id = brt.buildingid ");
			sql.append("\nINNER JOIN renttype rt ON brt.renttypeid = rt.id");
		}

	}

	public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if(!fieldName.equals("staffid") && !fieldName.equals("typeCode") && !fieldName.startsWith("rent")) {
					Object value = item.get(buildingSearchBuilder);
					if(value != null) {
						if(item.getType().getName().equals("java.lang.Long")  ||  item.getType().getName().equals("java.lang.Integer")) {
							where.append("\nAND b." + fieldName + " = " + value);
						}
						else {
							where.append("\nAND b." + fieldName + " LIKE '%" + value +"%' ");
						}
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		Long staffid = buildingSearchBuilder.getStaffid();
		if (staffid != null) {
			where.append("\nAND amb.staffid = " + staffid);
		}

		Long rentAreaTo = buildingSearchBuilder.getRentareato();
		Long rentAreaFrom = buildingSearchBuilder.getRentareafrom();

		if (rentAreaFrom != null || rentAreaTo != null) {
			where.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE b.id = ra.buildingid ");
			if (rentAreaFrom != null) {
				where.append("\nAND ra.value >= " + rentAreaFrom);
			}

			if (rentAreaTo != null) {
				where.append("\nAND ra.value <= " + rentAreaTo);
			}
			where.append(") ");
		}

		Long rentPriceTo = buildingSearchBuilder.getRentpriceto();
		Long rentPriceFrom = buildingSearchBuilder.getRentpricefrom();
		if (rentPriceFrom != null) {
			where.append("\nAND b.rentprice >= " + rentPriceFrom);
		}

		if (rentPriceTo != null) {
			where.append("\nAND b.rentprice <= " + rentPriceTo);
		}

		List<String> typeCode = buildingSearchBuilder.getTypeCode();

		if (typeCode != null && typeCode.size() != 0) {
//			where.append("\nAND rt.code IN ('" + String.join(",", typeCode) + "') ");

			where.append("\nAND ( ");
			String sql = typeCode.stream().map(it -> "rt.code LIKE " + "'%" + it + "%'")
					.collect(Collectors.joining(" OR "));
			where.append(sql + " ) ");
		}
	}

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {

		StringBuilder sql = new StringBuilder(
				"SELECT b.id, b.name, b.street, b.ward, b.districtid, b.numberofbasement, b.floorarea, b.rentprice,\n"
						+ "b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee FROM building b ");
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1");
		queryNormal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
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
//				building.setRentArea(rs.getLong("rentarea"));
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
