package org.house.predict.client;
import java.sql.*;
import java.util.*;

import org.house.predict.model.CityMasterModel;
import org.house.predict.service.CityMasterService;


public class PredictionClientApplication {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		CityMasterService cms=new CityMasterService();
		do
		{
			System.out.println("1)Add New City");
			System.out.println("2)View All City");
//			System.out.println(")Exit");
			System.out.println("Enter your choice...");
			int choice=sc.nextInt();
			sc.nextLine();
			switch(choice)
			{
			case 1:
				System.out.println("Enter name of city");
				String name=sc.nextLine();
				CityMasterModel model = new CityMasterModel();
				model.setCityName(name);
				boolean b=cms.isAddCity(model);
				if(b)
				{
					System.out.println("New City Added succesfully");
				}
				else
				{
					System.out.println("City not added");
				}
				break;
			case 2:
				List<CityMasterModel> listcity=cms.getAllCities();
				if(listcity!=null)
				{
					listcity.forEach((model1)->System.out.println(model1.getCityId()+"\t"+model1.getCityName()));
				}
				else
				{
					System.out.println("There is no city present");
				}
				break;
			default:System.out.println("Enter correct choice");
			}
			
		}while(true);
		
	}

}
