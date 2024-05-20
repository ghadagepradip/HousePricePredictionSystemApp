package org.house.predict.service;

import org.house.predict.model.AreaSquareFeetModel;
import org.house.predict.repository.AreaSquareFeetRepository;

public class AreaSquareFeetServices {
	AreaSquareFeetRepository areasq=new AreaSquareFeetRepository();
	
	public boolean isAddSquareFeet(AreaSquareFeetModel model)
	{
		return areasq.isAddSquareFeet(model);
	}

}
