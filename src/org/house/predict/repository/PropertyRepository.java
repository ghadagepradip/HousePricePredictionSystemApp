package org.house.predict.repository;
import java.util.*;

import org.house.predict.config.DBHelper;
import org.house.predict.model.AminityModel;
import org.house.predict.model.DealModel;
import org.house.predict.model.PropertyModel;

public class PropertyRepository extends DBHelper {
	
	public boolean isAddNewProperty(PropertyModel model)
	{
		int pid=model.getId();
		String propertyname=model.getName();
		int sqid=model.getSqmodel().getId();
		int areaid=model.getAreamodel().getAid();
		int cityid=model.getAreamodel().getCityid();
		int nbed=model.getNbed();
		int nbath=model.getNbath();
		System.out.println("property master");
		System.out.println("Id\tName\tsquare feet\tArea id\tcity id\tnbed\tnbath");
		System.out.println((pid+1)+"\t"+propertyname+"\t"+sqid+"\t"+areaid+"\t"+cityid+"\t"+nbed+"\t"+nbath);
		List<AminityModel>list=model.getList();
		System.out.println("Aminities");
		int count=0;
		for(AminityModel m:list)
		{
			count=count+(m.getId()+1);
			System.out.println(count+"\t"+m.getName());
		}
		System.out.println("property aminity relationship");
		count=0;
		pid++;
		for(AminityModel m:list)
		{
			count=count+(m.getId()+1);
			System.out.println(pid+"\t"+count);
		}
		System.out.println("property and price relationship");
		DealModel dealmodel=model.getDmodel();
		System.out.println((dealmodel.getTransid()+1)+"\t"+dealmodel.getPrice()+"\t"+dealmodel.getDate());
		
		return true;
	}

}
