package org.house.predict.repository;

import org.house.predict.config.DBHelper;
import org.house.predict.model.AminityModel;

public class AminityRepository extends DBHelper {
	
	public boolean isAddAminity(AminityModel model)
	{
		try 
		{
			stmt=conn.prepareStatement("insert into aminitymaster values('0',?);");
			stmt.setString(1, model.getName());
			int value=stmt.executeUpdate();
			return value>0?true:false;
		}
		catch(Exception e)
		{
			System.out.println("Error is "+e);
			return false;
		}
	}
	public int getAminityId(String name)
	{
		try
		{
			stmt=conn.prepareStatement("Select amid from aminitymaster where aminity=?");
			stmt.setString(1, name);
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
			return 0;
		}
	}

}
