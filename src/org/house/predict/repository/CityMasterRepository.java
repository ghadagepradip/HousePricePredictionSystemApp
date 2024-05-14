package org.house.predict.repository;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import org.house.predict.config.DBConfig;
import org.house.predict.config.DBHelper;
import org.house.predict.config.PathHelper;
import org.house.predict.model.CityMasterModel;

public class CityMasterRepository extends DBHelper{

	
	/* this method can add city in database table name as citymaster and get data from CityMasterModel class*/
	public boolean isAddNewCity(CityMasterModel model)
	{
		try
		{
			stmt=conn.prepareStatement("insert into citymaster values('0',?);");
			stmt.setString(1,model.getCityName());
			int value = stmt.executeUpdate();
			return value>0?true:false;
			
		}
		catch(Exception ex)
		{
			System.out.println("Error is "+ex);
			return false;
		}
		
	}
	public List<CityMasterModel> getAllCities()
	{
		 List<CityMasterModel> list=new ArrayList<CityMasterModel>();
		try {
			stmt=conn.prepareStatement("select * from citymaster;");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				CityMasterModel model=new CityMasterModel();
				model.setCityId(rs.getInt(1));
				model.setCityName(rs.getString(2));
				list.add(model);
			}
			return list.size()>0?list:null;
		} catch (SQLException e) {
			System.out.println("Error is "+e);
			return null;
		}		
	}
	public boolean isAddBulkCity()
	{
		try
		{
			FileReader fr = new FileReader(PathHelper.path+"city.csv");
			BufferedReader br =new BufferedReader(fr);
			String line=null;
			int value=0;
			while((line=br.readLine())!=null)
			{
				String data[]=line.split(",");
				stmt=conn.prepareStatement("insert into citymaster values('0',?);");
				stmt.setString(1, data[1]);
				value=stmt.executeUpdate();
			}
			return value>0?true:false;
		}
		catch(Exception ex)
		{
			System.out.println("Error is "+ex);
		}
		
		return true;
	}
	
}
