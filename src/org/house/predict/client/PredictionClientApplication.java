package org.house.predict.client;
import java.sql.*;

import java.util.*;

import org.house.predict.model.*;
import org.house.predict.service.CityMasterService;


public class PredictionClientApplication {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		CityMasterService cms=new CityMasterService();
		do
		{
			System.out.println("1)Add New City");
			System.out.println("2)View All City");
			System.out.println("3)Add bulk Cities");
			System.out.println("4)Add New area");
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
			case 3:
				b=cms.isAddBulkCity();
				if(b)
				{
					System.out.println("Cities addesd succesfully");
				}
				else
				{
					System.out.println("Error while adding cities");
				}
				break;
			case 4:
				System.out.println("Enter city name");
				name=sc.nextLine();
				int id=cms.getCityID(name);
				if(id!=-1)
				{
					System.out.println("Enter a area name");
					String aname=sc.nextLine();
					int aid=cms.getAreaIdAutomatic();
					AreaMasterModel amodel=new AreaMasterModel();
					amodel.setAid(aid);
					amodel.setAreaname(aname);
					amodel.setCityid(id);
					b=cms.isAddArea(amodel);
					if(b)
					{
						System.out.println("Area added succesfully");
					}
					else
					{
						System.out.println("Area not added succesfully");
					}	
				}
				else
				{
					System.out.println("City not present");
					System.out.println("Do you want to add this new city");
					String choice1=sc.nextLine();
					if(choice1.equals("yes"))
					{
						model = new CityMasterModel();
						model.setCityName(name);
						b=cms.isAddCity(model);
						if(b)
						{
							System.out.println("New City Added succesfully");
						}
						else
						{
							System.out.println("City not added");
						}
					}
					else
					{
						System.out.println("Thank you....");
					}
				}
				
				break;
			default:System.out.println("Enter correct choice");
			}
			
		}while(true);
		
	}

}
