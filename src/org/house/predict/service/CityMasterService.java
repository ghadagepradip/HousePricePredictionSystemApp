package org.house.predict.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.house.predict.model.AreaMasterModel;
import org.house.predict.model.CityMasterModel;
import org.house.predict.repository.CityMasterRepository;

public class CityMasterService {
	
	CityMasterRepository cityrepo=new CityMasterRepository();
	
	public boolean isAddCity(CityMasterModel Model)
	{
		return cityrepo.isAddNewCity(Model);
	}
	
	public List<CityMasterModel> getAllCities()
	{
		return cityrepo.getAllCities();
	}
	public boolean isAddBulkCity()
	{
		return cityrepo.isAddBulkCity();
	}
	public int getCityID(String cityname)
	{
		return cityrepo.getCityID(cityname);
	}
	public int getAreaIdAutomatic()
	{
		return cityrepo.getAreaIdAutomatic();
	}
	public boolean isAddArea(AreaMasterModel amodel)
	{
		return cityrepo.isAddArea(amodel);
	}
	public LinkedHashMap<String,Integer> getCityWiseAreaCount()
	{
		return cityrepo.getCityWiseAreaCount();
	}
	public LinkedHashMap<String,ArrayList<String>> getCityWiseAreaName()
	{
		return cityrepo.getCityWiseAreaName();
	}
	public int getAreaIdByName(AreaMasterModel model)
	{
		return cityrepo.getAreaIdByName(model);
	}

}
