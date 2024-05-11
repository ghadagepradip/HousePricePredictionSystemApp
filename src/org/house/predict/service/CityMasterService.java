package org.house.predict.service;

import java.util.List;

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

}
