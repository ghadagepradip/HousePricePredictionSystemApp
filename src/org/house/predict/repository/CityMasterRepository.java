package org.house.predict.repository;

import org.house.predict.config.DBConfig;
import org.house.predict.config.DBHelper;
import org.house.predict.model.CityMasterModel;

public class CityMasterRepository extends DBHelper{

	/* this method can add city in database table name as citymaster and get data from CityMasterModel class*/
	public boolean isAddNewCity(CityMasterModel model)
	{
		try
		{
			stmt=conn.prepareStatement("");
		}
		catch(Exception ex)
		{
			System.out.println("Error is "+ex);
			return false;
		}
		return false;
	}
	
}
