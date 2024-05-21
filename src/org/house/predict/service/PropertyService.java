package org.house.predict.service;

import org.house.predict.model.PropertyModel;
import org.house.predict.repository.PropertyRepository;

public class PropertyService {

	PropertyRepository proprepo=new PropertyRepository();
	public boolean isAddNewProperty(PropertyModel model)
	{
		return proprepo.isAddNewProperty(model);
	}
}
