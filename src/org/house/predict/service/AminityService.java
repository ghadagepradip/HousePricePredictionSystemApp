package org.house.predict.service;

import org.house.predict.model.AminityModel;
import org.house.predict.repository.AminityRepository;

public class AminityService {
	
	AminityRepository amrepo = new AminityRepository();
	
	public boolean isAddAminity(AminityModel model)
	{
		return amrepo.isAddAminity(model);
	}
	public int getAminityId(String name)
	{
		return amrepo.getAminityId(name);
	}

}
