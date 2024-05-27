package org.house.predict.repository;

import java.sql.SQLException;

import org.house.predict.config.DBHelper;
import org.house.predict.model.AreaSquareFeetModel;

public class AreaSquareFeetRepository extends DBHelper {
	public boolean isAddSquareFeet(AreaSquareFeetModel model)
	{
		try {
			stmt=conn.prepareStatement("insert into areasquarefeet values('0',?);");
			stmt.setInt(1, model.getSquareFeet());
			int value=stmt.executeUpdate();
			return value>0?true:false;			
			
		} catch (SQLException e) {
			System.out.println("Error is "+e);
			return false;
		}
	}
	public int getSquareFeetId(int landarea)
	{
		try {
			stmt=conn.prepareStatement("Select sqid from areasquarefeet where areainfeet=?");
			stmt.setInt(1, landarea);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
				
			}
			else
			{
				return 0;
			}
		} catch (SQLException e) {
			System.out.println("Error is "+e);
			return -1;
		}

	}

}
