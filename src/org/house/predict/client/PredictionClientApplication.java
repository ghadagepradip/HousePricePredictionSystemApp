package org.house.predict.client;

import java.sql.*;

import java.util.*;

import org.house.predict.model.*;
import org.house.predict.service.AminityService;
import org.house.predict.service.AreaSquareFeetServices;
import org.house.predict.service.CityMasterService;
import org.house.predict.service.PropertyService;

public class PredictionClientApplication {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		CityMasterService cms = new CityMasterService();
		AreaSquareFeetServices areasq = new AreaSquareFeetServices();
		AminityService aservice = new AminityService();
		PropertyService propservice=new PropertyService();
		do {
			System.out.println("1)Add New City");
			System.out.println("2)View All City");
			System.out.println("3)Add bulk Cities");
			System.out.println("4)Add New area");
			System.out.println("5)City Wise Area Count");
			System.out.println("6)City Wise Area name");
			System.out.println("7)Add area in square feet");
			System.out.println("8)Add new Aminities");
			System.out.println("9)Add new property");
			System.out.println("10)Area wise property count");
//			System.out.println(")Exit");
			System.out.println("Enter your choice...");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.println("Enter name of city");
				String name = sc.nextLine();
				CityMasterModel model = new CityMasterModel();
				model.setCityName(name);
				boolean b = cms.isAddCity(model);
				if (b) {
					System.out.println("New City Added succesfully");
				} else {
					System.out.println("City not added");
				}
				break;
			case 2:
				List<CityMasterModel> listcity = cms.getAllCities();
				if (listcity != null) {
					listcity.forEach((model1) -> System.out.println(model1.getCityId() + "\t" + model1.getCityName()));
				} else {
					System.out.println("There is no city present");
				}
				break;
			case 3:
				b = cms.isAddBulkCity();
				if (b) {
					System.out.println("Cities addesd succesfully");
				} else {
					System.out.println("Error while adding cities");
				}
				break;
			case 4:
				System.out.println("Enter city name");
				name = sc.nextLine();
				int id = cms.getCityID(name);
				if (id != -1) {
					System.out.println("Enter a area name");
					String aname = sc.nextLine();
					int aid = cms.getAreaIdAutomatic();
					AreaMasterModel amodel = new AreaMasterModel();
					amodel.setAid(aid);
					amodel.setAreaname(aname);
					amodel.setCityid(id);
					b = cms.isAddArea(amodel);
					if (b) {
						System.out.println("Area added succesfully");
					} else {
						System.out.println("Area not added succesfully");
					}
				} else {
					System.out.println("City not present");
					System.out.println("Do you want to add this new city");
					String choice1 = sc.nextLine();
					if (choice1.equals("yes")) {
						model = new CityMasterModel();
						model.setCityName(name);
						b = cms.isAddCity(model);
						if (b) {
							System.out.println("New City Added succesfully");
						} else {
							System.out.println("City not added");
						}
					} else {
						System.out.println("Thank you....");
					}
				}

				break;

			case 5:
				LinkedHashMap<String, Integer> map = cms.getCityWiseAreaCount();
				Set<Map.Entry<String, Integer>> set = map.entrySet();
				for (Map.Entry<String, Integer> m : set) {
					System.out.println(m.getKey() + "\t" + m.getValue());
				}

				break;
			case 6:
				LinkedHashMap<String, ArrayList<String>> m1 = cms.getCityWiseAreaName();
				Set<Map.Entry<String, ArrayList<String>>> set1 = m1.entrySet();
				for (Map.Entry<String, ArrayList<String>> e1 : set1) {

					ArrayList<String> a1 = e1.getValue();
					if (a1.size() > 0) {
						System.out.println("city Name: " + e1.getKey());
						System.out.println("======================+===================");
						for (String name1 : a1) {
							System.out.println(name1);
						}
						System.out.println("======================+===================");
						System.out.println();
					}

				}
				break;
			case 7:
				System.out.println("Enter area in squarefeet");
				int area=sc.nextInt();
				sc.nextLine();
				AreaSquareFeetModel areamodel=new AreaSquareFeetModel();
				areamodel.setSquareFeet(area);
				b=areasq.isAddSquareFeet(areamodel);
				if(b)
				{
					System.out.println("Area added succesfully.....");
				}
				else
				{
					System.out.println("Error while adding Area");
				}
				break;
			case 8:
				System.out.println("Enter name of aminity");
				name=sc.nextLine();
				AminityModel amodel=new AminityModel();
				amodel.setName(name);
				b=aservice.isAddAminity(amodel);
				if(b)
				{
					System.out.println("Aminity added succesfully");
				}
				else
				{
					System.out.println("Error while adding aminity");
				}
				break;
			case 9:
				System.out.println("Enter city name");
				String cityname=sc.nextLine();
				System.out.println("Enter area name");
				String areaname=sc.nextLine();
				System.out.println("Enter address of property");
				String add=sc.nextLine();
				System.out.println("Enter land area");
				int landarea=sc.nextInt();
				System.out.println("Enter number of bed and bath");
				int nbed=sc.nextInt();
				int nbath=sc.nextInt();
				sc.nextLine();
				int cityId=cms.getCityID(cityname);
				AreaMasterModel m=new AreaMasterModel();
				m.setAreaname(areaname);
				m.setCityName(cityname);
				int areaid=cms.getAreaIdByName(m);
//				System.out.println(areaid);	
				m.setCityid(cityId);
				m.setAid(areaid);
				int sqid=areasq.getSquareFeetId(landarea);
				if(sqid==-1)
				{
					System.out.println("there is some exception");
				}
				else if(sqid==0)
				{
					System.out.println("Square area not present do you want to add new squarearea");
				}
				
					List<AminityModel>aminitylist=new ArrayList<AminityModel>();
					String str="";
					
					do {
						
						System.out.println("Enter aminity name");
						String aname=sc.nextLine();
						AminityModel amModel=new AminityModel();
						int amid = aservice.getAminityId(aname);
						amModel.setId(amid);
						amModel.setName(aname);
						aminitylist.add(amModel);
						System.out.println("Do you want to add more aminities");
						str=sc.nextLine();
					}while(str.equals("yes"));
				
				PropertyModel propmodel = new PropertyModel();
				propmodel.setAreamodel(m);
				propmodel.setName(add);
				propmodel.setNbath(nbath);
				propmodel.setNbed(nbed);
				areamodel=new AreaSquareFeetModel();
				areamodel.setId(sqid);
				areamodel.setSquareFeet(landarea);
				propmodel.setSqmodel(areamodel);
				propmodel.setList(aminitylist);
				System.out.println("Enter price ");
				int price=sc.nextInt();
				DealModel dmodel=new DealModel();
				dmodel.setPrice(price);
//				dmodel.setDate(rdate);
				propmodel.setDmodel(dmodel);
				
				b=propservice.isAddNewProperty(propmodel);
				
				if(b)
				{
					System.out.println("Property Added succesfully");
				}
				else
				{
					System.out.println("Property Not Added....");
				}
				break;
			case 10:
				System.out.println("Enter city name");
				String city=sc.nextLine();
				List<Object[]>propcount=propservice.getareawisepropertycount(city);
				System.out.println("Areaname\tpropertycount");
				for(Object obj[]:propcount)
				{
					System.out.println(obj[0]+"\t"+obj[1]);
				}
				break;
			default:
				System.out.println("Enter correct choice");
			}

		} while (true);

	}

}
