package org.house.predict.repository;

import java.io.*;
import java.sql.*;
import java.sql.SQLException;
import java.util.*;
import org.house.predict.config.DBConfig;
import org.house.predict.config.DBHelper;
import org.house.predict.config.PathHelper;
import org.house.predict.model.AreaMasterModel;
import org.house.predict.model.CityMasterModel;

public class CityMasterRepository extends DBHelper {

	private int areaid = 0;
	private LinkedHashMap<String,Integer>map;
	LinkedHashMap<String,ArrayList<String>>citywiseareaname;

	/*
	 * this method can add city in database table name as citymaster and get data
	 * from CityMasterModel class
	 */
	public boolean isAddNewCity(CityMasterModel model) {
		try {
			stmt = conn.prepareStatement("insert into citymaster values('0',?);");
			stmt.setString(1, model.getCityName());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;

		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return false;
		}

	}

	public List<CityMasterModel> getAllCities() {
		List<CityMasterModel> list = new ArrayList<CityMasterModel>();
		try {
			stmt = conn.prepareStatement("select * from citymaster;");
			rs = stmt.executeQuery();
			while (rs.next()) {
				CityMasterModel model = new CityMasterModel();
				model.setCityId(rs.getInt(1));
				model.setCityName(rs.getString(2));
				list.add(model);
			}
			return list.size() > 0 ? list : null;
		} catch (SQLException e) {
			System.out.println("Error is " + e);
			return null;
		}
	}

	public boolean isAddBulkCity() {
		try {
			FileReader fr = new FileReader(PathHelper.path + "city.csv");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			int value = 0;
			while ((line = br.readLine()) != null) {
				String data[] = line.split(",");
				stmt = conn.prepareStatement("insert into citymaster values('0',?);");
				stmt.setString(1, data[1]);
				value = stmt.executeUpdate();
			}
			return value > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
		}

		return true;
	}

	public int getCityID(String cityname) {
		try {
			stmt = conn.prepareStatement("select cityid from citymaster where cityname=?");
			stmt.setString(1, cityname);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("cityid");
			} else
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int getAreaIdAutomatic() {
		try {
			stmt = conn.prepareStatement("select max(aid) from areamaster");
			rs = stmt.executeQuery();
			if (rs.next()) {
				this.areaid = rs.getInt(1);
			}
			areaid++;
			return areaid;

		} catch (Exception ex) {
			return 0;
		}

	}
	public boolean isAddArea(AreaMasterModel amodel)
	{
		try
		{
			stmt=conn.prepareCall("{call savearea(?,?,?)}");
			stmt.setInt(1, amodel.getAid());
			stmt.setString(2, amodel.getAreaname());
			stmt.setInt(3,amodel.getCityid());
			boolean b=stmt.execute();//retrn false if executed properly
			return !b;
		}
		catch(Exception ex)
		{
			System.out.println("Error while adding area...");
			return false;
		}
	}
	public LinkedHashMap<String,Integer> getCityWiseAreaCount()
	{
		this.map=new LinkedHashMap<String,Integer>();
		try {
			stmt=conn.prepareStatement(" select cm.cityname,count(caj.cityid) from citymaster cm inner join"
					+ " cityareajoin caj on cm.cityid=caj.cityid inner join areamaster am on caj.aid=am.aid"
					+ " group by cm.cityname;");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				map.put(rs.getString(1), rs.getInt(2));
			}
			return map;
			
		} catch (SQLException e) {
			System.out.println("Error is "+e);
			return null;
			
		}
		
	}
	public LinkedHashMap<String,ArrayList<String>> getCityWiseAreaName()
	{
		try {
			this.citywiseareaname=new LinkedHashMap<String,ArrayList<String>>();
			PreparedStatement pstmt=conn.prepareStatement("select cityname from citymaster;");
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				String cityname=rs.getString(1);
//				System.out.println(cityname);
				stmt=conn.prepareStatement(" select am.areaname from citymaster cm inner join cityareajoin caj on"
						+ " cm.cityid=caj.cityid inner join areamaster am on am.aid=caj.aid where cityname=?;");
				stmt.setString(1, cityname);
				ResultSet rs1=stmt.executeQuery();
//				System.out.println(rs1.next());
//				if(rs1.next())
//				{
					ArrayList<String>areaname=new ArrayList<String>();
					while(rs1.next())
					{
						areaname.add(rs1.getString(1));
					}
					this.citywiseareaname.put(cityname, areaname);
//				}
			}
			return citywiseareaname;
		} catch (SQLException e) {
			System.out.println("Error is "+e);
			return null;
		}
		
	}
	public int getAreaIdByName(AreaMasterModel model)
	{
		try
		{
			stmt=conn.prepareStatement("select am.aid from areamaster am inner join cityareajoin caj on caj.aid=am.aid inner join citymaster cm on caj.cityid=cm.cityid where am.areaname=? and cm.cityname=?;");
			stmt.setString(1, model.getAreaname());
			stmt.setString(2, model.getCityName());
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
			}
			else
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			System.out.println("Error is "+e);
			return -1;
		}
	}
	

}
