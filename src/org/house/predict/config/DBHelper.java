package org.house.predict.config;
import java.sql.*;

public class DBHelper {
	protected DBConfig db=DBConfig.getDBInstance();
	protected Connection conn=db.getConnection();
	protected PreparedStatement stmt=db.getStatement();
	protected ResultSet rs=db.getResultSet();
	

}
