package org.house.predict.repository;
import java.util.*;

import org.house.predict.config.DBHelper;
import org.house.predict.model.AminityModel;
import org.house.predict.model.DealModel;
import org.house.predict.model.PropertyModel;

public class PropertyRepository extends DBHelper {
	int pid;
	List<Object[]> areawisepcount;
	public int propIDAuto()
	{
		
		try {
			stmt=conn.prepareStatement("select max(pid) from propertymaster;");
			rs=stmt.executeQuery();
			if(rs.next())
			{
				pid=rs.getInt(1);
			}
			return ++pid;			
		}
		catch(Exception e)
		{
			System.out.println("Error1 is "+e);
			return 0;
		}
	}
	
	public boolean isAddNewProperty(PropertyModel model)
	{
		int pid=this.propIDAuto();
		String propertyname=model.getName();
		int sqid=model.getSqmodel().getId();
		int areaid=model.getAreamodel().getAid();
		int cityid=model.getAreamodel().getCityid();
		int nbed=model.getNbed();
		int nbath=model.getNbath();
		System.out.println("property master");
		System.out.println("Id\tName\tsquare feet\tArea id\tcity id\tnbed\tnbath");
		System.out.println((pid)+"\t"+propertyname+"\t"+sqid+"\t"+areaid+"\t"+cityid+"\t"+nbed+"\t"+nbath);
		try
		{
			stmt=conn.prepareStatement("insert into propertymaster values(?,?,?,?,?,?,?);");
			stmt.setInt(1,pid);
			stmt.setString(2,propertyname);
			stmt.setInt(3,sqid);
			stmt.setInt(4,areaid);
			stmt.setInt(5,cityid);
			stmt.setInt(6, nbed);
			stmt.setInt(7, nbath);
			int result=stmt.executeUpdate();
			if(result>0)
			{
				List<AminityModel>list=model.getList();
				int count=0;
				for(AminityModel m:list)
				{
					stmt=conn.prepareStatement("insert into propertyaminityjoin values(?,?);");
					stmt.setInt(1, pid);
					stmt.setInt(2,m.getId());
					int value=stmt.executeUpdate();
				}
				DealModel dealmodel=model.getDmodel();
				Date d=dealmodel.getDate();
				int price=dealmodel.getPrice();
				stmt=conn.prepareStatement("insert into propertyhistoricalprices values('0',?,?,(select curDate()));");
				stmt.setInt(1, pid);
				stmt.setInt(2, price);
				int value=stmt.executeUpdate();
				if(value>0)
				{
					System.out.println("Transaction added succesfully...");
					return true;
				}
				else
				{
					System.out.println("Error in adding transaction");
					return false;
				}
				
			}
			else
			{
				System.out.println("Error in adding property...");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Error is "+e);
			return false;
		}

	}
	public List<Object[]> getareawisepropertycount(String name)
	{
		try
		{
			this.areawisepcount =new ArrayList<Object[]>();
			stmt=conn.prepareStatement(" select am.areaname,count(pm.pid) from areamaster am inner join cityareajoin caj on caj.aid=am.aid inner join citymaster cm on cm.cityid=caj.cityid inner join propertymaster pm on pm.aid=am.aid where cm.cityname='"+name+"' group by am.areaname;");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				Object obj[]=new Object[] {rs.getString(1),rs.getInt(2)};
				this.areawisepcount.add(obj);
				
			}
			return areawisepcount;
		}
		catch(Exception e)
		{
			System.out.println("Error is "+e);
			return null;
		}
	}

}
